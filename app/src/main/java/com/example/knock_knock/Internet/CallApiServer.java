package com.example.knock_knock.Internet;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.knock_knock.Component.Const;
import com.example.knock_knock.Component.DeviceViewModel;
import com.example.knock_knock.DTO.UserInfo;
import com.example.knock_knock.IndexActivity;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiServer {

    private final DeviceViewModel mViewModel;
    private final IndexActivity mActivity;

    public CallApiServer(Activity activity) {

        this.mActivity = (IndexActivity) activity;
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) activity, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication()))
                .get(DeviceViewModel.class);
    }

    public void callEnter() {

        HashMap<String, String> body = new HashMap<>();
        body.put("user_name", Const.USER_NAME);
        body.put("door_mac", Const.USER_MAC);

        Call<JsonObject> call = RetrofitClient.getApiService().openDoor(body);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                int id = response.body().getAsJsonObject("data").get("id").getAsInt();
                String token = response.body().getAsJsonObject("data").get("token").getAsString();

                UserInfo userInfo = new UserInfo(Const.USER_NAME, id);
                userInfo.setToken(token);
                mViewModel.setUserInfo(userInfo);

                mActivity.changeFragment("HOME");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("ERROR", call.toString());
            }
        });

    }
}
