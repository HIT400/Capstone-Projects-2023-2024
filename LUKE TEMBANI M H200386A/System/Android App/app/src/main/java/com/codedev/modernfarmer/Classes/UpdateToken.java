package com.codedev.modernfarmer.Classes;

public class UpdateToken {

    private String token;
    private String username;

    public UpdateToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UpdateToken{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
