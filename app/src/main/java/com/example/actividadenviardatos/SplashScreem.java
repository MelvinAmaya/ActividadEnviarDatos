package com.example.actividadenviardatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;
import android.widget.VideoView;

public class SplashScreem extends AppCompatActivity {
    VideoView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screem);
        video = (VideoView) findViewById(R.id.vdFondo);

        video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sendhelp));
        video.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        },5000);

    }
}