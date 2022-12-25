package com.javohirbekcoder.intent2quizapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.javohirbekcoder.intent2quizapp.databinding.ActivityGameOverBinding;

import java.util.MissingFormatArgumentException;

public class GameOverActivity extends AppCompatActivity {

    private ActivityGameOverBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#3677D4"));
        actionBar.setBackgroundDrawable(colorDrawable);

        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        binding.correctAnswers.setText("You answered " +  correctAnswers + " out of 10 questions correctly!");
        binding.tryAgainbtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}