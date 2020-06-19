package com.example.myapplication;

import okhttp3.Headers;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String URL = "https://community-ebh.herokuapp.com/posts/";
    public static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGVObyI6IjU1NTU1NTU1NTUiLCJ1c2VySWQiOiI1ZWU4ZTE5OGVlYzlkNzBjODY0NWI5OWEiLCJpYXQiOjE1OTI1MzE3NjEsImV4cCI6MTU5MjU1MzM2MX0.ifn_913b9B3Ex3MztDtGa0ll4D87-YET5ZjoLUQk0i0";
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
