package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;

public class Question extends AppCompatActivity {

    // Storing pictures and answers
    final int[] questionPics = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    final String[] answers = {"Toronto", "Charlottetown", "St. John\'s", "Yellowknife"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        // Grab info from prior intent
        Bundle extraValues = getIntent().getExtras();
        if (extraValues != null) {
            int maxQ = (int) extraValues.get("maxQ");
            int currQ = (int) extraValues.get("currQ");

            // Get the radio button text based on the question
            String[] questions;
            if (currQ == 1)
                questions = getResources().getStringArray(R.array.q1);
            else if (currQ == 2)
                questions = getResources().getStringArray(R.array.q2);
            else if (currQ == 3)
                questions = getResources().getStringArray(R.array.q3);
            else
                questions = getResources().getStringArray(R.array.q4);

            // Set the radio button text
            RadioButton q1 = findViewById(R.id.q1);
            q1.setText(questions[0]);

            RadioButton q2 = findViewById(R.id.q2);
            q2.setText(questions[1]);

            RadioButton q3 = findViewById(R.id.q3);
            q3.setText(questions[2]);

            RadioButton q4 = findViewById(R.id.q4);
            q4.setText(questions[3]);

            // Set the button text to either next question, or submit quiz
            Button finish = findViewById(R.id.submit);
            if (currQ == maxQ)
                finish.setText("Submit");
            else
                finish.setText("Next");

            // Set the picture to the corresponding question
            ImageView pic = findViewById(R.id.questionPic);
            pic.setImageResource(questionPics[currQ - 1]);
        }
    }

    public void submit(View view) {
        String answer;

        // Checking the answer, if there is one
        RadioGroup radioGroup = findViewById(R.id.questions);             // Get radio group
        int questionButtonId = radioGroup.getCheckedRadioButtonId();      // Get answer button id
        if(questionButtonId == -1) {                                      // By default, the id is -1 if nothing is selected
            Toast.makeText(getApplicationContext(), "Must select an answer", Toast.LENGTH_LONG).show();
            return;
        } else {
            RadioButton selectedAnswer = findViewById(questionButtonId);  // Get id of the selected button
            answer = (String) selectedAnswer.getText();                   // Convert text of the button to a string
        }

        // Grab the info from the prior intent
        Bundle extraValues = getIntent().getExtras();
        if (extraValues != null) {
            int maxQ = (int) extraValues.get("maxQ");
            int currQ = (int) extraValues.get("currQ");
            int correctCount = (int) extraValues.get("correctCount");

            // Check selected answer, and the corresponding correct answer for the question
            if(answer.equals(answers[currQ - 1]))
                correctCount++;

            // If the question is the final question, move to the summary intent
            if(currQ == maxQ) {
                Intent finish = new Intent(Question.this, Summary.class);

                finish.putExtra("maxQ", maxQ);
                finish.putExtra("correctCount", correctCount);

                startActivity(finish);
            }
            // Else, move to the next question
            else {
                Intent nextQ = new Intent(Question.this, Question.class);

                // Pass along max questions, increment current questions, and correct amount of questions
                nextQ.putExtra("maxQ", maxQ);
                nextQ.putExtra("currQ", ++currQ);
                nextQ.putExtra("correctCount", correctCount);

                startActivity(nextQ);
            }
        }
    }
}