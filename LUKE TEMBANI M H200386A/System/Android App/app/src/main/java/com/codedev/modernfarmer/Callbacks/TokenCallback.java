package com.codedev.modernfarmer.Callbacks;

public interface TokenCallback {

    void onSuccess(String token);
    void onFailure(String error);
}
