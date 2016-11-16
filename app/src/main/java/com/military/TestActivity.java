package com.military;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;


import java.io.File;

/**
 * Created by lichengcai on 2016/11/16.
 */

public class TestActivity extends Activity {
    private VideoView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imageView = (VideoView) findViewById(R.id.videoView);

        imageView.setVideoURI(Uri.fromFile(new File("/sdcard/cc00.mp4")));
        imageView.start();
        imageView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(TestActivity.this,"video is finish",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
