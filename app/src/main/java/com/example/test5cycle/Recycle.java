package com.example.test5cycle;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Recycle extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //declare video
        videoView = findViewById(R.id.video_view);

        //get video from res path ://raw where vid is stored
        //vid from local file
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);

        //display vid
        //set Anchor View
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }
}