package com.example.abdelrahmansamir.broadcastreciver;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.shut_up_and_drive);
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        mediaPlayer.stop();
        super.onStop();
    }
}
