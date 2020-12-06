package com.example.knock_knock.Component;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.knock_knock.Internet.CallApiServer;

import java.util.concurrent.Executor;

public class AuthManager {

    private Context mContext;
    private Executor mMainExecutor;
    private BiometricPrompt mBiometricPrompt;
    private BiometricPrompt.PromptInfo mBiometricPromptInfo;

    public AuthManager(Context mContext, CallApiServer apiServer) {
        this.mContext = mContext;

        mMainExecutor = ContextCompat.getMainExecutor(mContext);
        mBiometricPrompt = new BiometricPrompt((FragmentActivity) mContext, mMainExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                apiServer.callOpenDoor();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }
        });

        mBiometricPromptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Knock Knock")
                .setDeviceCredentialAllowed(false)
                .setNegativeButtonText("NO")
                .build();
    }

    public void runAuth() {
        mBiometricPrompt.authenticate(mBiometricPromptInfo);
    }
}
