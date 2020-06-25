package com.example.myapplication;

import okhttp3.Headers;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String URL = "https://community-ebh.herokuapp.com/";
    public static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGVObyI6IjU1NTU1NTU1NTUiLCJ1c2VySWQiOiI1ZWU4ZTE5OGVlYzlkNzBjODY0NWI5OWEiLCJpYXQiOjE1OTI4Nzk5ODEsImV4cCI6MTU5MjkwMTU4MX0.8IV8zwYfRThNZce2d8LGifxYcYEYlmk75vvc_AhI3ZQ";
    public static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}