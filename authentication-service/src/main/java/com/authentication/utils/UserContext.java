package com.authentication.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String AUTH_TOKEN = "auth-token";
    public static final String USER_ID = "user-id";

    private String authToken= new String();
    private String userId = new String();

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}