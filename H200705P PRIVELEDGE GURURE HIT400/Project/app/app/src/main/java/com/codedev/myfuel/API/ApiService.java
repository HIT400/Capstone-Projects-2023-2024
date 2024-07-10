package com.codedev.myfuel.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/")
    Call<Void> testServer();

}
