package com.raju.vpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class Plater1Activity extends AppCompatActivity
{
    private ImageView ivBackArrow;
    private VideoView videoView;
    private  MyMediaController mediaController;
    private String videoUrl = "";
    private ProgressBar progressBarVideo;
    private View view;


    class MyMediaController extends MediaController
    {

        public MyMediaController(Context context)
        {
            super(context);
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent event)
        {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
            {
                ((Activity) getContext()).finish();
            }

            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plater1);

        initViews();
        setViews();
    }

    private void initViews()
    {
        ivBackArrow = findViewById(R.id.ivBackArrow);
        videoView = findViewById(R.id.videoView);
        progressBarVideo = findViewById(R.id.progressBarVideo);
        view = findViewById(R.id.view);
        mediaController = new MyMediaController(this);

        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        ivBackArrow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    private void setViews()
    {
//        videoUrl = "https://seenit-lc-dev.s3.ap-southeast-1.amazonaws.com/posts/VID-20191123-WA0008.mp4";
        videoUrl = "https://seenit-lc-dev.s3.ap-southeast-1.amazonaws.com/posts/VIDEO_20200420_201432.mp4";

        if (!TextUtils.isEmpty(videoUrl))
        {
            videoView.setVideoURI(Uri.parse(videoUrl));
            videoView.start();
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.start();
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener()
                {

                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2)
                    {
                        progressBarVideo.setVisibility(View.GONE);
                        mp.start();
                    }
                });
            }
        });

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mediaController.isShowing())
                {
                    mediaController.hide();
                }
                else
                {
                    mediaController.show(3000);
                }
            }
        });
    }
}
