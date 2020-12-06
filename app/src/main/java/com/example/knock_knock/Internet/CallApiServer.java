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
                userInfo.setToken(token);
                mViewModel.setUserInfo(userInfo);

                mActivity.changeFragment("HOME");
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
                JsonObject body = response.body();
                JsonArray history = response.body().get("history").getAsJsonArray();

                List<LogInfo> result = new ArrayList<>();
                for (JsonElement element : history) {
                    int parseInout = element.getAsJsonObject().get("check_enterout").getAsInt();

                    String access_time = element.getAsJsonObject().get("access_time").getAsString();
                    String date = access_time.split("T")[0];
                    String time = access_time.split("T")[1].split("Z")[0];

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

    // Device 명단
    public void callDeviceList(UserInfo userInfo) {
        HashMap<String, Integer> body = new HashMap<>();
        body.put("user_id", userInfo.getId());
        Call<JsonArray> call = mApiService.callGetDeviceList(body);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray array = response.body();

                List<DeviceInfo> result = new ArrayList<>();

                for (int i = 0; i < array.size(); i++) {

                    JsonElement element = array.get(i);

                    JsonObject asJsonObject = element.getAsJsonObject();
                    String device_id = asJsonObject.get("device_id").getAsString();
                    String device_type = asJsonObject.get("device_type").getAsString();
                    String device_name = asJsonObject.get("device_name").getAsString();
                    int start_setting = asJsonObject.get("start_setting").getAsInt();

                    DeviceInfo deviceInfo = new DeviceInfo(device_name, device_id, device_type);
                    deviceInfo.setLevel(start_setting);

                    result.add(deviceInfo);
                }

                mViewModel.setDeviceInfoList(result);
                mViewModel.setCurDevice(result.get(0));

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }

    // Device 제어
    public void callToggleDevice(DeviceInfo deviceInfo) {
        Call<JsonObject> call = mApiService.callToggleDevice(deviceInfo.getId(), deviceInfo.getType());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    // 퇴장
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
