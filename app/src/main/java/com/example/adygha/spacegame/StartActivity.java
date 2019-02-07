package com.example.adygha.spacegame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartActivity extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.start_activity);

        Button startButton = (Button)findViewById(R.id.start);
        startButton.setOnClickListener(this);

        Button exitButton = (Button)findViewById(R.id.exit);
        exitButton.setOnClickListener(this);
    }

    /** Обработка нажатия кнопок */
    public void onClick(View v) {
        switch (v.getId()) {
            //переход на сюрфейс
            case R.id.start: {
                Intent intent = new Intent();
                intent.setClass(this, GameActivity.class);
                startActivity(intent);
            }break;

            //выход
            case R.id.about: {
                finish();
            }break;

            default:
                break;
        }
    }
}