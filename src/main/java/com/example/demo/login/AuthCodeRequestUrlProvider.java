package com.example.demo.login;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}