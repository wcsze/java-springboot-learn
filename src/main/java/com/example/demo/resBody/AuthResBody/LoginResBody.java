package com.example.demo.resBody.AuthResBody;

public class LoginResBody {
    private String accessToken;
    private String refereshToken;

    public LoginResBody(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refereshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefereshToken() {
        return refereshToken;
    }
}
