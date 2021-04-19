package com.example.week10_32039;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button projectOne, projectTwo, projectThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        projectOne = findViewById(R.id.buttonProject1);
        projectTwo = findViewById(R.id.buttonProject2);
        projectThree = findViewById(R.id.buttonProject3);

        projectOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, firstProjectAct.class);
                startActivity(intent);
            }
        });

        projectTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, secProjectAct.class);
                startActivity(intent);
            }
        });

        projectThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, thirdProjectAct.class);
                startActivity(intent);
            }
        });
    }
}