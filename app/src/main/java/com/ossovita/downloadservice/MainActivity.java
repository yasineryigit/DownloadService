package com.ossovita.downloadservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void download(View v){
        startService(new Intent(this,DownloadService.class));
    }
    public void stop(View v){
        stopService(new Intent(this,DownloadService.class));
    }
}