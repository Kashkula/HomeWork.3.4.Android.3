package com.example.lesson1android3.data.network;
import com.example.lesson1android3.data.interfaces.PostApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApiService {

    private static final String BASE_URL = "https://android-3-mocker.herokuapp.com/";
    private static PostApiService postApiService;
    protected Retrofit retrofit;

    public static PostApiService getInstance() {
        if (postApiService == null) {
            postApiService = new PostApiService();
        }
        return postApiService;
    }

    private PostApiService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PostApi getPostApi() {
        return retrofit.create(PostApi.class);
    }


}
