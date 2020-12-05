package com.example.knock_knock.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.knock_knock.Component.AuthManager;
import com.example.knock_knock.Component.BluetoothService;
import com.example.knock_knock.Component.DeviceViewModel;
import com.example.knock_knock.IndexActivity;
import com.example.knock_knock.Internet.CallApiServer;
import com.example.knock_knock.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {

    private CallApiServer callApiServer;
    private FragmentAuthBinding mBinding;
    private DeviceViewModel mViewModel;
    private MutableLiveData<Boolean> isDoorFound;
    private BluetoothService mBluetoothService;

    public AuthFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        mViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
//                .get(DeviceViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentAuthBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        callApiServer = new CallApiServer(requireActivity());

        isDoorFound = new MutableLiveData<Boolean>();
        mBluetoothService = new BluetoothService(isDoorFound);
        mBluetoothService.startScan();

        isDoorFound.observe(this, isFound -> {
            if(isFound) {
                ((IndexActivity)getActivity()).changeFragment("HOME");
            }
        });

        mBinding.btn.setOnClickListener(view -> {
            new AuthManager(requireContext(), callApiServer).runAuth();
        });
    }
}