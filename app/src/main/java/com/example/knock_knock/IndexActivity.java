package com.example.knock_knock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.knock_knock.Fragment.AuthFragment;
import com.example.knock_knock.Fragment.ExitFragment;
import com.example.knock_knock.Fragment.HomeFragment;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        changeFragment("AUTH");
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    public void changeFragment(String name) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (name) {
            case "AUTH" : {
                transaction.replace(R.id.frame, new AuthFragment()).commit();
                break;
            }

            case "HOME" : {
                transaction.replace(R.id.frame, new HomeFragment()).commit();
                break;
            }

            case "EXIT" : {
                transaction.replace(R.id.frame, new ExitFragment()).commit();
                break;
            }
        }
    }

}