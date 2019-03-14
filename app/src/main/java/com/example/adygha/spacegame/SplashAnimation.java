package com.example.adygha.spacegame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashAnimation extends AppCompatActivity {

    private ImageView container;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_animation);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        container = findViewById(R.id.iv_icons);
        container.setBackgroundResource(R.drawable.splash_animation);

        animationDrawable = (AnimationDrawable) container.getBackground();
    }


    @Override
    protected void onResume() {
        super.onResume();

        animationDrawable.start();

        checkAnimationStatus(50, animationDrawable);
    }


    private void checkAnimationStatus(final int time, final AnimationDrawable animationDrawable) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (animationDrawable.getCurrent() != animationDrawable.getFrame(animationDrawable.getNumberOfFrames() - 1))
                    checkAnimationStatus(time, animationDrawable);
                else finish();
            }
        }, time);
    }
}
