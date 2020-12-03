package com.example.knock_knock;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.concurrent.Executor;

public class AuthFragment extends Fragment {

    private BiometricPrompt.PromptInfo mBiometricPromptInfo;
    private Executor mMainExecutor;
    private BiometricPrompt mBiometricPrompt;
    private Context mContext;

    public AuthFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = requireContext();


        mMainExecutor = ContextCompat.getMainExecutor(mContext);
        mBiometricPrompt = new BiometricPrompt(this, mMainExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(mContext, "AUTH SUCCESS", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(mContext, "FAIL AUTH", Toast.LENGTH_LONG).show();
            }
        });



        mBiometricPromptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Knock Knock")
                .setSubtitle("문을 열기 위해 생체 인증을 진행해주세요")
                .setDeviceCredentialAllowed(false)
                .setNegativeButtonText("NO")
                .build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}