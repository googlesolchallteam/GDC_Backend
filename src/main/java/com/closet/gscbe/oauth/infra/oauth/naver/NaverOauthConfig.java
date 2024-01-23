package com.closet.gscbe.oauth.infra.oauth.naver;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.naver")
public record NaverOauthConfig(
        String redirectUri,
        String clientId,
        String clientSecret,
        String[] scope,
        String state
) {
}
