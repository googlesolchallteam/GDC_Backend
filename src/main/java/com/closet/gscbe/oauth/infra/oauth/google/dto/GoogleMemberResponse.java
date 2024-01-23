package com.closet.gscbe.oauth.infra.oauth.google.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.closet.gscbe.oauth.domain.OauthId;
import com.closet.gscbe.oauth.domain.OauthMember;

import static com.closet.gscbe.oauth.domain.OauthServerType.GOOGLE;

@JsonNaming(value = SnakeCaseStrategy.class)
public record GoogleMemberResponse(
        String id,
        String name,
        String givenName,
        String picture,
        String locale
) {

    public OauthMember toDomain() {
        return OauthMember.builder()
                .oauthId(new OauthId(id, GOOGLE))
                .nickname(givenName)
                .profileImageUrl(picture)
                .build();
    }
}
