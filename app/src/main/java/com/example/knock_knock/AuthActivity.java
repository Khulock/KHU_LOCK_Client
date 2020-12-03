package com.example.knock_knock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class AuthActivity extends AppCompatActivity {

    private Context mContext;
    private Executor mMainExecutor;
    private BiometricPrompt mBiometricPrompt;
    private BiometricPrompt.PromptInfo mBiometricPromptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mContext = this;


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

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
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
    public void onStart() {
        super.onStart();
        findViewById(R.id.btn).setOnClickListener(view -> {
            mBiometricPrompt.authenticate(mBiometricPromptInfo);
        });
    }

}