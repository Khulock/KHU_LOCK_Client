package com.example.knock_knock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.Service.DeviceListAdapter;
import com.example.knock_knock.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        List<DeviceInfo> list = new ArrayList<>();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setName("AAAAA");
        list.add(deviceInfo);

        DeviceInfo deviceInfo2 = new DeviceInfo();
        deviceInfo2.setName("BBBB");
        list.add(deviceInfo2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        mBinding.recycleDeviceItem.setLayoutManager(layoutManager);

        DeviceListAdapter deviceListAdapter = new DeviceListAdapter(list, requireContext());
        mBinding.recycleDeviceItem.setAdapter(deviceListAdapter);
    }
}