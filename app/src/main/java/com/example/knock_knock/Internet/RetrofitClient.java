package com.example.knock_knock.Internet;

import com.example.knock_knock.Const;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// morm.tistory.com/296

public class RetrofitClient {

    public static ApiService getApiService() {
        return getInstance().create(ApiService.class);
    }
    public static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(Const.Base_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
