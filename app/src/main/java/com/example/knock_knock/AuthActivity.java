package com.example.knock_knock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.knock_knock.Service.AuthManager;

import java.util.concurrent.Executor;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_item);

//        getSupportFragmentManager().beginTransaction().add(R.id.frame, new AuthFragment()).commit();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

}