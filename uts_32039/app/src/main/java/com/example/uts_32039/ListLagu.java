package com.example.uts_32039;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.zip.Inflater;

import static com.example.uts_32039.LoginPage.loginDone;
import static com.example.uts_32039.MainActivity.fileMusiks;

public class ListLagu extends AppCompatActivity {
    RecyclerView recyclerView;
    MusicAdapter musicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lagu);
        if(loginDone == true){
            showDialog();
            loginDone = false;
        }
        else if(loginDone == false){
            loginDone = false;
        }
        recyclerView = (RecyclerView) findViewById(R.id.rvMusic);
        recyclerView.setHasFixedSize(true);
        if(!(fileMusiks.size() < 1)){
            musicAdapter = new MusicAdapter(this, fileMusiks);
            recyclerView.setAdapter(musicAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome!");
        builder.setMessage("Muhammad Faisal Farhan - 00000032039");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = builder.create();

        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profileMenu:
                Intent intent = new Intent(this, ProfilePage.class);
                startActivity(intent);
                return true;
            case R.id.logoutMenu:
                Intent intents = new Intent(this, LoginPage.class);
                startActivity(intents);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}


