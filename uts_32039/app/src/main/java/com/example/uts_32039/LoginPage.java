package com.example.uts_32039;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    private EditText username, password;
    private Button btnLogin;

    static boolean loginDone;
    String nama = "uasmobile";
    String kunci = "uasmobilegenap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.loginBtn);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals(nama) && password.getText().toString().equals(kunci)){
                    Intent intent = new Intent(LoginPage.this, ListLagu.class);
                    startActivity(intent);
                    loginDone = true;
                } else{
                    Toast.makeText(LoginPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}