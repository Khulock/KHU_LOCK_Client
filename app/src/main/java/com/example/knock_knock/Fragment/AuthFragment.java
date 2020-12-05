package com.example.knock_knock.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.knock_knock.Component.AuthManager;
import com.example.knock_knock.Component.DeviceViewModel;
import com.example.knock_knock.Internet.CallApiServer;
import com.example.knock_knock.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {

    private CallApiServer callApiServer;
    private FragmentAuthBinding mBinding;
    private DeviceViewModel mViewModel;

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
        mBinding.btn.setOnClickListener(view -> {
            new AuthManager(requireContext(), callApiServer).runAuth();
        });
    }
}