package com.example.act4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,pw;
    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.textUsername);
        pw = (EditText)findViewById(R.id.txtPassword);
        display = (Button)findViewById(R.id.btnDisplay);
        display.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "your name: " + name.getText() +
                                "\nYour password: " + pw.getText(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
