package com.example.knock_knock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        getSupportFragmentManager().beginTransaction().add(R.id.frame, new AuthFragment()).commit();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

}