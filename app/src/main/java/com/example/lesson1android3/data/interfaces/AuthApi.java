package com.example.lesson1android3.data.interfaces;

import com.example.lesson1android3.data.model.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AuthApi {
    @GET("user")
    Call<UserModel> getUser(@Header("Authorization") String id);

}
