package com.example.week08_32039;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button Week08A, Week08B;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Week08A = (Button) findViewById(R.id.week8a);
        Week08B = (Button) findViewById(R.id.week8b);

        Week08A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Week8A.class);
                startActivity(intent);
            }
        });
        Week08B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Week8B.class);
                startActivity(intent);
            }
        });

    }
}