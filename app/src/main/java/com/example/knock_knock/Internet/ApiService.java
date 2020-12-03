package com.example.knock_knock.Internet;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/hhhh")
    Call<JsonArray> getTestData();
}
