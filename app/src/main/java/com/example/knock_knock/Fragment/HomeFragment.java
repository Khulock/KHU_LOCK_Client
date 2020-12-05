package com.example.knock_knock.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knock_knock.Component.BluetoothService;
import com.example.knock_knock.Component.ControlDialogInterface;
import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.Component.DeviceListAdapter;
import com.example.knock_knock.Component.DeviceViewModel;
import com.example.knock_knock.IndexActivity;
import com.example.knock_knock.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ControlDialogInterface{

    private FragmentHomeBinding mBinding;
    private DeviceViewModel mViewModel;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
                .get(DeviceViewModel.class);
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
        DeviceInfo deviceInfo = new DeviceInfo("AAA", "BBB");
        list.add(deviceInfo);

        DeviceInfo deviceInfo2 = new DeviceInfo("CCC", "DDD");
        list.add(deviceInfo2);

        mViewModel.setDeviceInfoList(list);
        mViewModel.setCurDevice(list.get(0));




        mBinding.btnOut.setOnClickListener(view -> {
            ((IndexActivity)getActivity()).changeFragment("EXIT");
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());

        mBinding.recycleDeviceItem.setLayoutManager(layoutManager);
        DeviceListAdapter deviceListAdapter = new DeviceListAdapter(mViewModel.getDeviceInfoList(), requireContext(), this);
        mBinding.recycleDeviceItem.setAdapter(deviceListAdapter);


    }


    @Override
    public boolean changeLevel(int input) {
        mViewModel.getCurDevice().setLevel(input);
        return false;
    }
}