package com.example.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface Wall_API {

    @GET("posts")
    Call<List<Post>> getPosts(@HeaderMap Map<String, String> headers);
}


