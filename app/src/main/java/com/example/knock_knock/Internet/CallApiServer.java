package com.example.knock_knock.Internet;

import android.app.Activity;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.knock_knock.Component.Const;
import com.example.knock_knock.Component.DeviceViewModel;
import com.example.knock_knock.DTO.UserInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiServer {

    private final DeviceViewModel mViewModel;

    public CallApiServer(Activity activity) {
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) activity, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication()))
                .get(DeviceViewModel.class);
    }

    public void callEnter() {

        HashMap<String, Object> body = new HashMap<>();
        body.put("user_name", Const.NAME);
        body.put("door_mac", "AA:BB:CC:DD:EE:FF");

        Call<JsonObject> call = RetrofitClient.getApiService().openDoor(body);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                int id = response.body().getAsJsonObject("data").get("id").getAsInt();
                String token = response.body().getAsJsonObject("data").get("token").getAsString();

                UserInfo userInfo = new UserInfo(Const.NAME, id);
                userInfo.setToken(token);
                mViewModel.setUserInfo(userInfo);
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                call.request().body();
            }
        });

    }
}
