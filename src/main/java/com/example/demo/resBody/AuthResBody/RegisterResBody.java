package com.example.demo.resBody.AuthResBody;

public class RegisterResBody {
    private String accessToken;
    private String refereshToken;

    public RegisterResBody(String accessToken, String refreshToken){
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
