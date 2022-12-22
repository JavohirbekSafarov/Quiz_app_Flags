package com.javohirbekcoder.intent2quizapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.javohirbekcoder.intent2quizapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final int[] images = Database.images;
    private final String[] options = Database.answers;
    ArrayList<Integer> randomOptions = new ArrayList<>();
    private final int[] radioButtons = {
            R.id.firstAnswer,
            R.id.secondAnswer,
            R.id.thirdAnswer,
            R.id.fourthAnswer
    };

    private int questionNumber = 1;
    private int correctAnswers = 0;
    private String correctAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Quiz App");

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#3677D4"));
        actionBar.setBackgroundDrawable(colorDrawable);

        generateQuestion();
        binding.btnNext.setOnClickListener(v -> {
            questionNumber++;
            switch (binding.radioGroup.getCheckedRadioButtonId()){
                case R.id.firstAnswer:
                    if (binding.firstAnswer.getText().toString().equals(correctAnswer.toString())){
                        showMessage("You answered correctly!");
                        correctAnswers++;
                        YoYo.with(Techniques.Bounce)
                                .duration(1000) //700
                                .repeat(1)
                                .playOn(findViewById(R.id.correctAnswer));
                        generateQuestion();
                    }else {
                        showMessage("You gave the wrong answer");
                        generateQuestion();
                    }
                    break;
                case R.id.secondAnswer:
                    if (binding.secondAnswer.getText().toString().equals(correctAnswer.toString())){
                        showMessage("You answered correctly!");
                        correctAnswers++;
                        YoYo.with(Techniques.Bounce)
                                .duration(1000) //700
                                .repeat(1)
                                .playOn(findViewById(R.id.correctAnswer));
                        generateQuestion();
                    }else {
                        showMessage("You gave the wrong answer");
                        generateQuestion();
                    }
                    break;
                case R.id.thirdAnswer:
                    if (binding.thirdAnswer.getText().toString().equals(correctAnswer.toString())){
                        showMessage("You answered correctly!");
                        correctAnswers++;
                        YoYo.with(Techniques.Bounce)
                                .duration(1000) //700
                                .repeat(1)
                                .playOn(findViewById(R.id.correctAnswer));
                        generateQuestion();
                    }else {
                        showMessage("You gave the wrong answer");
                        generateQuestion();
                    }
                    break;
                case R.id.fourthAnswer:
                    if (binding.fourthAnswer.getText().toString().equals(correctAnswer.toString())){
                        showMessage("You answered correctly!");
                        correctAnswers++;
                        YoYo.with(Techniques.Bounce)
                                .duration(1000) //700
                                .repeat(1)
                                .playOn(findViewById(R.id.correctAnswer));
                        generateQuestion();
                    }else {
                        showMessage("You gave the wrong answer");
                        generateQuestion();
                    }
                    break;
            }
            if (questionNumber > 10){
                binding.questionId.setText(""+10);
                finishGame();
            }
        });
        binding.btnFinish.setOnClickListener(v -> finishGame());
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                binding.btnNext.setEnabled(true);
            }
        });
    }

    private void finishGame() {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("correctAnswers", correctAnswers);
        startActivity(intent);
        finish();
    }

    private void generateQuestion() {
        if (questionNumber != 11){
            Random random = new Random();

            YoYo.with(Techniques.BounceIn)
                    .duration(700) //700
                    .repeat(1)
                    .playOn(findViewById(R.id.questionId));
            binding.questionId.setText("" + questionNumber);
            binding.correctAnswer.setText("" + correctAnswers);
            binding.imageView.setImageResource(images[questionNumber - 1]);
            correctAnswer = options[questionNumber - 1];

            //Generate correct answer loaction
            int locationCorrectAnswer = random.nextInt(4);
            RadioButton correctAnswerBtn = (RadioButton) findViewById(radioButtons[locationCorrectAnswer]);
            correctAnswerBtn.setText(correctAnswer.toString());
            randomOptions.add(questionNumber - 1);

            //Generate incorrect answer loactions
            for (int i = 0; i < 4; i++) {
                if (locationCorrectAnswer != i){
                    RadioButton radioButton = (RadioButton) findViewById(radioButtons[i]);

                    int myRand = random.nextInt(10);
                    while (randomOptions.contains(myRand)){
                        myRand = random.nextInt(10);
                    }
                    randomOptions.add(myRand);
                    radioButton.setText(options[myRand]);
                }
            }
            randomOptions.clear();
        }
    }

    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}