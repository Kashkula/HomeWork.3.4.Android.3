package com.example.lesson1android3.data.network;

import com.example.lesson1android3.data.interfaces.AuthApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthApiService {
    private static final String BASE_URL = "https://api.github.com/";

    private static AuthApiService authApiService;

    protected Retrofit retrofit;

    public static AuthApiService getInstance() {
        if (authApiService == null) {
            authApiService = new AuthApiService();
        }
        return authApiService;
    }

    private AuthApiService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public AuthApi getAuthApi() {
        return retrofit.create(AuthApi.class);
    }
}
