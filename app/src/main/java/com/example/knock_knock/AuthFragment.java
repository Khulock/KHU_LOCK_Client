package com.example.knock_knock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.knock_knock.Auth.AuthManager;
import com.example.knock_knock.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {

    private FragmentAuthBinding mBinding;

    public AuthFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mBinding.btn.setOnClickListener(view -> {
            new AuthManager(requireContext()).runAuth();
        });
    }
}