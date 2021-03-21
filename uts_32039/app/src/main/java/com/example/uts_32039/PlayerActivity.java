package com.example.uts_32039;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.uts_32039.MainActivity.fileMusiks;

public class PlayerActivity extends AppCompatActivity {
    TextView song_name, artist_name, duration_played, duration_left;
    ImageView coverArt, skipPrev, skipNext, back;
    SeekBar sb;
    FloatingActionButton play_pause;

    int position = -1;
    static ArrayList<FileMusik> listSongs = new ArrayList<>();
    static Uri uri;
    static MediaPlayer mp;
    private Handler handler = new Handler();
    private Thread playThr, prevThr, nextThr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        song_name = findViewById(R.id.song_name);
        artist_name = findViewById(R.id.song_artist);
        duration_played = findViewById(R.id.durationPlayer);
        duration_left = findViewById(R.id.durationleft);
        coverArt = findViewById(R.id.artCover);
        skipPrev = findViewById(R.id.skip_previous);
        skipNext = findViewById(R.id.skip_next);
        back = findViewById(R.id.backBtn);
        sb = findViewById(R.id.seekbarDuration);
        play_pause = findViewById(R.id.playPause);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent intent = new Intent(PlayerActivity.this, ListLagu.class);
                startActivity(intent);
            }
        });

        getIntentMethod();
        song_name.setText(listSongs.get(position).getTitle());
        artist_name.setText(listSongs.get(position).getArtist());
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mp != null && fromUser){
                    mp.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mp != null){
                    int CurrentPos = mp.getCurrentPosition()/1000;
                    sb.setProgress(CurrentPos);
                    duration_played.setText(formattedTime(CurrentPos));
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onResume() {
        playThrBtn();
        nextThrBtn();
        prevThrBtn();
        super.onResume();
    }

    private void prevThrBtn() {
        prevThr = new Thread(){
            @Override
            public void run() {
                super.run();
                skipPrev.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        prevBtnClicked();
                    }
                });
            }
        };
        prevThr.start();
    }

    private void prevBtnClicked() {
        if(mp.isPlaying()){
            mp.stop();
            mp.release();
            position = ((position - 1) < 0 ? (listSongs.size() - 1) : (position-1));
            uri = Uri.parse(listSongs.get(position).getPath());
            mp = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            song_name.setText(listSongs.get(position).getTitle());
            artist_name.setText(listSongs.get(position).getArtist());
            sb.setMax(mp.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mp != null){
                        int CurrentPos = mp.getCurrentPosition()/1000;
                        sb.setProgress(CurrentPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            play_pause.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
            mp.start();
        } else {
            mp.stop();
            mp.release();
            position = ((position - 1) < 0 ? (listSongs.size() - 1) : (position-1));
            uri = Uri.parse(listSongs.get(position).getPath());
            mp = MediaPlayer.create(getApplicationContext(),uri);
            metaData(uri);
            song_name.setText(listSongs.get(position).getTitle());
            artist_name.setText(listSongs.get(position).getArtist());
            sb.setMax(mp.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mp != null){
                        int CurrentPos = mp.getCurrentPosition()/1000;
                        sb.setProgress(CurrentPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            play_pause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
    }

    private void nextThrBtn() {
        nextThr = new Thread(){
            @Override
            public void run() {
                super.run();
                skipNext.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        nextBtnClicked();
                    }
                });
            }
        };
        nextThr.start();
    }

    private void nextBtnClicked() {
        if(mp.isPlaying()){
            mp.stop();
            mp.release();
            position = ((position + 1) % listSongs.size());
            uri = Uri.parse(listSongs.get(position).getPath());
            mp = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            song_name.setText(listSongs.get(position).getTitle());
            artist_name.setText(listSongs.get(position).getArtist());
            sb.setMax(mp.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mp != null){
                        int CurrentPos = mp.getCurrentPosition()/1000;
                        sb.setProgress(CurrentPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            play_pause.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
            mp.start();
        } else {
            mp.stop();
            mp.release();
            position = ((position + 1) % listSongs.size());
            uri = Uri.parse(listSongs.get(position).getPath());
            mp = MediaPlayer.create(getApplicationContext(),uri);
            metaData(uri);
            song_name.setText(listSongs.get(position).getTitle());
            artist_name.setText(listSongs.get(position).getArtist());
            sb.setMax(mp.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mp != null){
                        int CurrentPos = mp.getCurrentPosition()/1000;
                        sb.setProgress(CurrentPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            play_pause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
    }

    private void playThrBtn() {
        playThr = new Thread(){
            @Override
            public void run() {
                super.run();
                play_pause.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        playPauseBtnClicked();
                    }
                });
            }
        };
        playThr.start();
    }

    private void playPauseBtnClicked(){
        if(mp.isPlaying()){
            play_pause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
            mp.pause();
            sb.setMax(mp.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mp != null){
                        int CurrentPos = mp.getCurrentPosition()/1000;
                        sb.setProgress(CurrentPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        } else {
            play_pause.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
            mp.start();
            sb.setMax(mp.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mp != null){
                        int CurrentPos = mp.getCurrentPosition()/1000;
                        sb.setProgress(CurrentPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }
    }

    private String formattedTime(int CurrentPos){
        String total = "";
        String totalNew= "";
        String seconds = String.valueOf(CurrentPos % 60);
        String minutes = String.valueOf(CurrentPos/60);
        total = minutes + "." + seconds;
        totalNew = minutes + "."+ "0" + seconds;
        if(seconds.length() == 1){
            return totalNew;
        } else {
            return total;
        }
    }

    private void getIntentMethod() {
        position = getIntent().getIntExtra("position", -1);
        listSongs = fileMusiks;
        if(listSongs != null){
            play_pause.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
            uri = Uri.parse(listSongs.get(position).getPath());
        }
        if(mp != null){
            mp.stop();
            mp.release();
            mp = MediaPlayer.create(getApplicationContext(),uri);
            mp.start();
        }else{
            mp = MediaPlayer.create(getApplicationContext(), uri);
            mp.start();
        }
        sb.setMax(mp.getDuration() / 1000);
        metaData(uri);
    }

    private void metaData (Uri uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri.toString());
        int totalduration = Integer.parseInt(listSongs.get(position).getDuration()) / 1000;
        duration_left.setText(formattedTime(totalduration));
        byte[] art = retriever.getEmbeddedPicture();
        if(art!=null){
            Glide.with(this).asBitmap().load(art).into(coverArt);
        } else{
            Glide.with(this).asBitmap().load(R.drawable.stafaband).into(coverArt);
        }
    }
}