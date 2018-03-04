package com.recommendation.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "user-id";

    private static final ThreadLocal<String> authToken = new ThreadLocal<String>();
    private static final ThreadLocal<String> userId = new ThreadLocal<String>();

    public static String getAuthToken() {
        return authToken.get();
    }

    public static void setAuthToken(String aToken) {
        authToken.set(aToken);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void setUserId(String aUser) {
        userId.set(aUser);
    }


}