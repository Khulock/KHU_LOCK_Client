package com.example.knock_knock.Internet;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.knock_knock.Component.Const;
import com.example.knock_knock.Component.AppViewModel;
import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.DTO.LogInfo;
import com.example.knock_knock.DTO.UserInfo;
import com.example.knock_knock.IndexActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiServer {

    private final AppViewModel mViewModel;
    private final IndexActivity mActivity;
    private final ApiService mApiService;

    public CallApiServer(Activity activity) {

        this.mActivity = (IndexActivity) activity;
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) activity, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication()))
                .get(AppViewModel.class);

        mApiService = RetrofitClient.getApiService();
    }

    public void callOpenDoor() {

        HashMap<String, String> body = new HashMap<>();
        body.put("user_name", Const.USER_NAME);
        body.put("door_mac", Const.USER_MAC);

        Call<JsonObject> call = mApiService.callOpenDoor(body);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) { return; }
                int id = response.body().getAsJsonObject("data").get("id").getAsInt();
                String token = response.body().getAsJsonObject("data").get("token").getAsString();
                UserInfo userInfo = new UserInfo(Const.USER_NAME, id);
                userInfo.setToken("Bearer " + token);
                mViewModel.setUserInfo(userInfo);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) { Log.d("ERROR", call.toString()); }
        });
    }

    public void callGetHistory() {

        Call<JsonObject> call = mApiService.callGetInoutLog(mViewModel.getUserInfo().getToken(), mViewModel.getUserInfo().getId());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray history = response.body().get("history").getAsJsonArray();

                List<LogInfo> result = new ArrayList<>();
                for (JsonElement element : history) {
                    int parseInout = element.getAsJsonObject().get("check_enterout").getAsInt();

                    String access_time = element.getAsJsonObject().get("access_time").getAsString();
                    String date = access_time.split("T")[0];
                    String time = access_time.split("T")[1].split(".")[0];

                    String parsedTime = date + " " + time;

                    result.add(new LogInfo(parsedTime, parseInout));
                }

                mViewModel.setLogInfoList(result);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void callToggleDevice(DeviceInfo deviceInfo) {
        Call<JsonObject> call = mApiService.callToggleDevice(deviceInfo.getId());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    public void callOutDoor(UserInfo user) {
        Call<JsonObject> call = mApiService.callOutDoor(user.getToken());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }





}
