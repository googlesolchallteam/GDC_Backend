package com.closet.gscbe.oauth.domain.authcode;

import com.closet.gscbe.oauth.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}
