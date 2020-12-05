package com.example.knock_knock.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knock_knock.Component.DeviceListAdapter;
import com.example.knock_knock.Component.DeviceViewModel;
import com.example.knock_knock.databinding.FragmentExitBinding;

public class ExitFragment extends Fragment {


    private FragmentExitBinding mBinding;
    private DeviceViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
                .get(DeviceViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentExitBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
//        mBinding.recyclerDeviceItem.setLayoutManager(new LinearLayoutManager(requireContext()));
//        mBinding.recyclerDeviceItem.setAdapter(new DeviceListAdapter(mViewModel.getDeviceInfoList(),requireContext()));
    }
}