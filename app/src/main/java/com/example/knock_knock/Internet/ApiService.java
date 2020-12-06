package com.example.knock_knock.Internet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/user/auth")
    Call<JsonObject> callOpenDoor(@Body HashMap<String, String> param);

    @GET("/user/history/{user_id}")
    Call<JsonObject> callGetInoutLog(
            @Header("Authorization") String token,
            @Path("user_id") Integer userId
    );

    @POST("/device/run/{device_id}/{device_type}")
    Call<JsonObject> callRunDevice(
            @Path("device_id") String deviceId,
            @Path("device_type") String type,
            @Body HashMap<String, Integer> body
    );

    @GET("/device/stop/{device_id}/{device_type}")
    Call<JsonObject> callStopDevice(
            @Path("device_id") String deviceId,
            @Path("device_type") String type
    );


    @GET("/user/out")
    Call<JsonObject> callOutDoor(
            @Header("Authorization") String token
    );

    @POST("/group/device")
    Call<JsonArray> callGetDeviceList(
            @Body HashMap<String, Integer> param
    );


}
