package com.example.demo.contoroller;

import com.example.demo.entity.MemberEntity;
import com.example.demo.login.OauthServerType;
import com.example.demo.service.OauthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class LoginController {
    private final OauthService oauthService;

    @GetMapping("/kakao")
    public ResponseEntity<Void> redirectUrl (HttpServletResponse response) throws IOException {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(OauthServerType.KAKAO);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<MemberEntity> login (@RequestParam("code") String code) throws JsonProcessingException {
        MemberEntity memberEntity = oauthService.getKakaoUserInfo(code);
        return new ResponseEntity<>(memberEntity, HttpStatus.OK);
    }
}

