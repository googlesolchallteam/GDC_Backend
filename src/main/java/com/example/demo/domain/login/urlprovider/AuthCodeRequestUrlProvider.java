package com.example.demo.domain.login.urlprovider;

import com.example.demo.domain.login.dto.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}