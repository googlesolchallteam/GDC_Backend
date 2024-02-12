package com.example.demo.service;

import com.example.demo.config.KakaoOauthConfig;
import com.example.demo.entity.MemberEntity;
import com.example.demo.login.AuthCodeRequestUrlProviderComposite;
import com.example.demo.login.OauthServerType;
import com.example.demo.login.OauthToken;
import com.example.demo.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final static String KAKAO_OAUTH_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final static String KAKAO_USER_PROFILE_URL = "https://kapi.kakao.com/v2/user/me";

    private final ObjectMapper objectMapper;

    private final KakaoOauthConfig kakaoOauthConfig;

    private final MemberRepository memberRepository;
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public MemberEntity getKakaoUserInfo(String code) throws JsonProcessingException {
        String accessToken = requestKakaoAccessToken(code);
        // Kakao 사용자 정보 요청
        String responseBody = requestKakaoUserProfile(accessToken);
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        System.out.println(jsonNode);
        long id = jsonNode.get("id").asLong();
        System.out.println(id);
        String nickname = jsonNode.path("properties").path("nickname").asText();
        String profileImage = jsonNode.path("properties").path("profile_image").asText();

        // 추출한 필드로 객체 생성
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setServerId(id);
        memberEntity.setNickname(nickname);
        memberEntity.setProfileImage(profileImage);

        memberRepository.save(memberEntity);
        return memberEntity;
    }

    private String requestKakaoUserProfile(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USER_PROFILE_URL,
                HttpMethod.POST,
                request,
                String.class
        );
        
        return response.getBody();
    }

    private String requestKakaoAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = getMultiValueMapHttpEntity(code);

        try {
        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_OAUTH_TOKEN_URL,
                HttpMethod.POST,
                request,
                String.class
        );
            OauthToken oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);
            return oauthToken.getAccess_token();
        } catch (JsonProcessingException e) {
            // JSON 처리 중 예외가 발생한 경우 예외 처리 로직 추가
            System.out.println(e.getMessage());
            return null; // 적절한 오류 처리를 수행해야 합니다.
        }
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", code);
        params.add("client_secret", kakaoOauthConfig.clientSecret());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        return request;
    }
}
