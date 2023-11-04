package com.example.diceroll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button enterButton;
    TextView oddTextView;
    TextView evenTextView;

    TextView textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterButton = findViewById(R.id.Enter_BTN);
        oddTextView = findViewById(R.id.OddOutput_TXT);
        evenTextView = findViewById(R.id.EvenOutput_TXT);
        textInputEditText = findViewById(R.id.textInputEditText);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });
    }
    private void rollDice() {
        String numRollsText = textInputEditText.getText().toString();

        // Check if the input is empty
        if (numRollsText.isEmpty()) {
            // Handle empty input (e.g., show an error message)
            return;
        }

        int numRolls = Integer.parseInt(numRollsText);

        int oddCount = 0;
        int evenCount = 0;

        // Seed the random number generator with the current time
        Random random = new Random(System.currentTimeMillis());
        String randNum = random.toString();


        for (int i = 0; i < numRolls; i++) {
            // Simulate rolling a dice (assuming a standard six-sided dice)
            int diceRoll = random.nextInt(6) + 1;

            if (diceRoll % 2 == 0) {
                evenCount++;
            }
            else {

                oddCount++;
            }
        }
        

        // Display the results in the TextViews
        oddTextView.setText("Odd outcomes: " + oddCount);
        evenTextView.setText("Even outcomes: " + evenCount);
    }
}