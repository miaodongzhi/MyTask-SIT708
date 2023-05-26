package com.example.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.AlertDialog;
import android.graphics.Color;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView QuestionOrderTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionList.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuestionOrderTextView = findViewById(R.id.question_order);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);

        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);

        submitBtn.setOnClickListener(this);

        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;

        submitBtn.setText("NEXT");

        if(clickedButton.getId()==R.id.submit_btn){
            currentQuestionIndex++;
            loadNewQuestion();
        }

        if(clickedButton.getId()==R.id.ans_A){

            //get the answer
            selectedAnswer = ansA.getText().toString();

            if(selectedAnswer.equals(QuestionList.correctAnswers[currentQuestionIndex])){
                //if correct
                ansA.setBackgroundColor(Color.GREEN);
                score++;
            }else {
                //if wrong
                ansA.setBackgroundColor(Color.RED);
            }

        }

        if(clickedButton.getId()==R.id.ans_B){

            selectedAnswer = ansB.getText().toString();

            if(selectedAnswer.equals(QuestionList.correctAnswers[currentQuestionIndex])){
                ansB.setBackgroundColor(Color.GREEN);
                score++;
            }else{
                ansB.setBackgroundColor(Color.RED);
            }
        }

        if(clickedButton.getId()==R.id.ans_C){

            selectedAnswer = ansC.getText().toString();

            if(selectedAnswer.equals(QuestionList.correctAnswers[currentQuestionIndex])){
                ansC.setBackgroundColor(Color.GREEN);
                score++;
            }else{
                ansC.setBackgroundColor(Color.RED);
            }
        }

    }


    void loadNewQuestion(){

        //Load the question

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        QuestionOrderTextView.setText(QuestionList.order[currentQuestionIndex]);
        questionTextView.setText(QuestionList.question[currentQuestionIndex]);
        ansA.setText(QuestionList.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionList.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionList.choices[currentQuestionIndex][2]);


    }

    void finishQuiz(){
        String passStatus = "";

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Congradulation! Your " + "Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setNegativeButton("Finish",(dialogInterface, i) -> stopQuiz())
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        //Restart
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

    void stopQuiz(){
        //Exit
        System.exit(0);
    }

}