package ru.olschegolev.retrofitgetandpost;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/posts")
    Call<List<Post>> getPosts(@Query("apiKey")String apikey);

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> createPost(@Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") int userid,
                          @Query("apiKey")String apikey);
}
