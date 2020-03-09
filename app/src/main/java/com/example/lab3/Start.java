package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        // Initializing spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.questionNum, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner divisionSpinner = findViewById(R.id.selectQSpinner);
        divisionSpinner.setAdapter(adapter);
    }

    public void startQuiz(View view) {
        // Get spinner value
        Spinner numQSpinner = findViewById(R.id.selectQSpinner);
        int numQ = Integer.parseInt(numQSpinner.getSelectedItem().toString());

        // Create intent to go to q1
        Intent q1 = new Intent(Start.this, Question.class);

        // Send max questions, current question, and correct count to q1
        q1.putExtra("maxQ", numQ);
        q1.putExtra("currQ", 1);
        q1.putExtra("correctCount", 0);

        // Start q1
        startActivity(q1);
    }
}