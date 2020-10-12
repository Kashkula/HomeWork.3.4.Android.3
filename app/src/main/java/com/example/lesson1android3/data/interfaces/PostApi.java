package com.example.lesson1android3.data.interfaces;

import com.example.lesson1android3.data.model.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApi {
    @GET("posts")
    Call<List<PostsModel>> getPosts();

    @POST("posts")
    Call<PostsModel> postData(@Body PostsModel model);

    @GET("posts/{postId}")
    Call<PostsModel> getPostWithID(@Path("postId") Integer postId);

    @DELETE("posts/{postId}")
    Call<PostsModel> deletePost(@Path("postId") Integer postId);

    @PUT("posts/{postId}")
    Call<PostsModel> updatePost(@Path("postId") Integer postId, @Body PostsModel model);

    @GET("posts?users")
    Call<List<PostsModel>> getUsers();

    @GET("posts?")
    Call<List<PostsModel>> getUserPosts(@Query("user") String user);
}
