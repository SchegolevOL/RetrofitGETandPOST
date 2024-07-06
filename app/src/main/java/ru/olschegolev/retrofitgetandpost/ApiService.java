package ru.olschegolev.retrofitgetandpost;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {



    @GET("/posts")
    Call<List<Post>> getPosts(@Header("Authorization") String authToken);

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> createPost(
            @Header("Authorization") String authToken,
            @Field("title") String title,
            @Field("body") String body,
            @Field("userId") int userid);
}
