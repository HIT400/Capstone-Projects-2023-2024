package com.codedev.myfuel.API;

import com.codedev.myfuel.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
        private static Retrofit retrofit;

        public RetrofitClient(){}

        public static synchronized Retrofit getClient(){
            if(retrofit == null){
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            return retrofit;
        }
    }

