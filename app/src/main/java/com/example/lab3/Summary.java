package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;


public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        Bundle extraValues = getIntent().getExtras();
        if (extraValues != null) {
            int maxQ = (int) extraValues.get("maxQ");
            int correctCount = (int) extraValues.get("correctCount");

            TextView score = findViewById(R.id.scoreCount);
            score.setText(correctCount + " / " + maxQ);
        }
    }

    public void finish(View view) {
        Intent nextQ = new Intent(Summary.this, Start.class);
        startActivity(nextQ);
    }
}
