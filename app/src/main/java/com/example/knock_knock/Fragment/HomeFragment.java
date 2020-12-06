package com.example.knock_knock.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knock_knock.Component.ControlDialogInterface;
import com.example.knock_knock.Component.LogAdapter;
import com.example.knock_knock.Component.DeviceListAdapter;
import com.example.knock_knock.Component.AppViewModel;
import com.example.knock_knock.IndexActivity;
import com.example.knock_knock.Internet.CallApiServer;
import com.example.knock_knock.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements ControlDialogInterface{

    private FragmentHomeBinding mBinding;
    private AppViewModel mViewModel;
    private CallApiServer mApiCall;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
                .get(AppViewModel.class);

        mApiCall = new CallApiServer(requireActivity());
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

        mApiCall.callDeviceList(mViewModel.getUserInfo());
        mApiCall.callGetHistory();


        mBinding.btnOut.setOnClickListener(view -> {
            ((IndexActivity)getActivity()).changeFragment("EXIT");
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());

        // Device List Recycler view binding
        mBinding.recycleDeviceItem.setLayoutManager(layoutManager);
        DeviceListAdapter deviceListAdapter = new DeviceListAdapter(mViewModel.getDeviceInfoList(), requireContext(), this, mViewModel);
        mBinding.recycleDeviceItem.setAdapter(deviceListAdapter);
        mViewModel.observeDeviceInfoList().observe(this, deviceInfos -> {
            deviceListAdapter.setDeviceList(deviceInfos);
            deviceListAdapter.notifyDataSetChanged();
        });

        // Log Recycler view binding
        LogAdapter logAdapter = new LogAdapter(null);
        mBinding.recycleLogItem.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.recycleLogItem.setAdapter(logAdapter);
        mViewModel.observeLogInfoList().observe(this, logInfoList -> {
            logAdapter.setLogs(logInfoList);
            logAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public boolean callbackControlDialog(String tag, int index) {

        mViewModel.getCurDevice().setLevel(index);
        mViewModel.setDeviceInfoList(mViewModel.getDeviceInfoList());
        if (mViewModel.getCurDevice().getLevel() == 0) {
            mApiCall.callStopDevice(mViewModel.getCurDevice());
        } else {
            mApiCall.callRunDevice(mViewModel.getCurDevice());
        }

        return false;
    }
}