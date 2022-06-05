package com.paz.razabi.driving;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
   private MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        mMediaPlayer.setLooping(false);
    }

    public void onStart(Intent i, int startId){
        Toast.makeText(this, "Music Started", Toast.LENGTH_SHORT).show();
        mMediaPlayer.start();
    }

    public void onDestroy(){
        Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show();
        mMediaPlayer.stop();
    }
}
