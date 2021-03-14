package com.example.week07b_32039;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DetailVideoActivity extends AppCompatActivity {
    private VideoView vvDetil;
    private EditText etJudul, etKeterangan, etUri;
    private Button btnKembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_video);
        vvDetil = (VideoView) findViewById(R.id.vvDetil);
        etJudul = (EditText) findViewById(R.id.etJudul);
        etKeterangan = (EditText) findViewById(R.id.etKeterangan);
        etUri = (EditText) findViewById(R.id.etUri);
        btnKembali = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SumberVideo sv = (SumberVideo) bundle.getSerializable("DetilVideo");
        etJudul.setText(sv.getJudul());
        etKeterangan.setText(sv.getKeterangan());
        etUri.setText(sv.getVideoURI());
        vvDetil.setVideoURI(Uri.parse(sv.getVideoURI()));
        vvDetil.seekTo(100);
        vvDetil.start();
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(vvDetil);
        vvDetil.setMediaController(controller);
        btnKembali.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
