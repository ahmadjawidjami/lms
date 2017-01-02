package com.tu.ziik.lms.service;

public interface SecurityService {
    String findAuthenticatedUsername();

    void autoLogin(String username, String password);
}
