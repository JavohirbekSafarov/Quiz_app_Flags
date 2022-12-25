package com.javohirbekcoder.intent2quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.javohirbekcoder.intent2quizapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private String[] welcometexts = {
            "",
            "Welcome",
            "flags",
            "quiz!"
    };
    private int index;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.welcomeText.setFactory(
                new ViewSwitcher.ViewFactory() {
                    @Override
                    public View makeView()
                    {
                        textView
                                = new TextView(
                                SplashActivity.this);
                        textView.setTextColor(Color.parseColor("#0E489C"));
                        textView.setTextSize(40);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        return textView;
                    }
                });
        binding.welcomeText.setText(welcometexts[index]);

        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (index == welcometexts.length - 1) {
                    index = 0;
                    binding.welcomeText.setText(welcometexts[index]);
                }
                else {
                    binding.welcomeText.setText(welcometexts[++index]);
                }
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }.start();
    }
}