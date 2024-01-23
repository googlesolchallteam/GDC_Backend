package com.closet.gscbe.oauth.domain.client;

import com.closet.gscbe.oauth.domain.OauthMember;
import com.closet.gscbe.oauth.domain.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}
