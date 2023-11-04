package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button add, close, clear; // List of button to use
    EditText num1, num2; // List of places where you can type
    TextView ans; // A special place on the screen to show the answer

    @Override //  This is a special word that tells the computer we want to change something that already exists.
    protected void onCreate(Bundle savedInstanceState) { // When our app starts, do the following things
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // show the main screen of our app, which is designed in a file called "activity_main.xml

        num1 = (EditText) findViewById(R.id.txtNum1); // Go find a specific EditText on the screen and put it in the num1
        num2 = (EditText) findViewById(R.id.txtNum2);
        ans = (TextView) findViewById(R.id.txtMessage);

        add = (Button) findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() { //  is telling the button that it should listen for clicks, and when a click happens, do something.
            @Override
            public void onClick(View view) {// is the set of instructions for what should happen when the button is clicked.
                int x, y, sum;

                x = Integer.parseInt(num1.getText().toString());
                y = Integer.parseInt(num2.getText().toString());
                sum = x + y;
                ans.setText(" " + sum);
                Toast.makeText(getApplicationContext(), "Sum is" + sum, Toast.LENGTH_SHORT).show();
            }
        });

        clear = (Button) findViewById(R.id.btnClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText("");
                num2.setText("");
                ans.setText("");
            }
        });

    }
}