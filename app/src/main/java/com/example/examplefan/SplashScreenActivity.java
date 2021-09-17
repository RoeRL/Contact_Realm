        package com.example.examplefan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    Handler handler;
    ProgressBar progressBar;
    Intent intent;
    int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.labelProgressbar);

        splashScreenProgress();
    }

    private void splashScreenProgress(){

        setProgressProgressing(progress);

    }

    private void setProgressProgressing(final int progress){

        progressBar.setProgress(progress);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressProgressing(progress + 25);
            }
        });
        thread.start();

        if (progress == 100) {
            startActivity(new Intent(SplashScreenActivity.this, SwitchActivity.class));
        }
    }
}