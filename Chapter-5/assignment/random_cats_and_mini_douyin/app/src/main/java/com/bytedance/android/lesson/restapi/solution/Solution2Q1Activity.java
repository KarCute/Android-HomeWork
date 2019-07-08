package com.bytedance.android.lesson.restapi.solution;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Solution2Q1Activity extends AppCompatActivity {

    private static final String TAG = "VideoActivity";
    private SurfaceView surfaceView;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private SeekBar seekBar;
    private ImageView imageView;
    private int position = 0;
    private Timer timer;
    private TimerTask timerTask;
    private int ifPortrait=0;

    //    private Button buttonPlay;
//    private Button buttonBack;
//    private Button buttonPause;
    private int playState = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MediaPlayer");
        setContentView(R.layout.activity_solution2_q1);

//        Intent intent = new Intent(Intent.ACTION_VIEW);

//        Uri uri = getIntent().getData();
//
//        Log.d(TAG, "onCreate: "+uri);


        seekBar = findViewById(R.id.seekBar);
        surfaceView = findViewById(R.id.surfaceView);
        imageView = findViewById(R.id.imageView);

        player = new MediaPlayer();

        try {
            Intent intent = getIntent();
            String videoUrl = intent.getStringExtra("ijkplayer_url");
//            player.setDataSource(getResources().openRawResourceFd(R.raw.yuminhong));
            player.setDataSource(videoUrl);
//            player.setDataSource(getApplicationContext(),uri);
            holder =  surfaceView.getHolder();
            holder.addCallback(new PlayerCallBack());
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.d(TAG, "onPrepared: ");
                    player.start();
                    player.setLooping(true);
                    player.pause();
                    seekBar.setMax(player.getDuration());
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            if(player!=null){
                                int time = player.getCurrentPosition();
                                seekBar.setProgress(time);
                            }
                        }
                    };
                    timer.schedule(timerTask,0,500);

                    seekBar.setProgress(position);
                    player.seekTo(position);
                    player.start();
                    player.setLooping(true);
                    player.pause();
                }

            });


            player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.d(TAG, "onBufferingUpdate: "+percent);
                    System.out.println(percent);

                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: "+seekBar.getProgress());
                if(player!=null){
                    player.seekTo(seekBar.getProgress());
                }
            }
        });

        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playState==0){
                    playState = 1;
                    player.start();
                    imageView.setVisibility(View.INVISIBLE);
                }else{
                    playState = 0;
                    player.pause();
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }



    private void stop(){
        if (player != null) {
            timerTask.cancel();
            timerTask = null;
            seekBar.setProgress(0);
            player.stop();
            player.release();
            player = null;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    private class PlayerCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.d(TAG, "surfaceCreated: ");
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG, "surfaceChanged: ");
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG, "surfaceDestroyed: ");
        }
    }


}