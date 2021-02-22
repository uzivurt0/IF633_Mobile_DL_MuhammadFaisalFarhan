package com.example.week04b_32039;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentmanager.beginTransaction();

        Fragment firstFragment = new FirstFragment();
        fragmentTransaction.replace(R.id.third_activity_fragment_1, firstFragment);

        Fragment secondFragment = new SecondFragment();
        fragmentTransaction.replace(R.id.third_activity_fragment_2, secondFragment);
        fragmentTransaction.commit();

    }
}