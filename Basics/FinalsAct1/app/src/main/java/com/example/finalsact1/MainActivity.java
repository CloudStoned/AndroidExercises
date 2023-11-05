package com.example.finalsact1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

        EditText employeeId, employeeFullName,employeeCurrentWeight,
                employeeHeight, employeeGoalWeight, employeeAge, employeePhoneNum, employeeAddress;
        Button search,submit;

        //GENDER
        RadioButton Male,Female,Others;
        DBHelper dbContext;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //EDIT TEXT
            employeeId = (EditText) findViewById(R.id.txt_employeeId);
            employeeFullName = (EditText) findViewById(R.id.txt_enterFullName);
            employeeCurrentWeight = (EditText) findViewById(R.id.txt_currentWeight);
            employeeHeight = (EditText) findViewById(R.id.txt_Height);
            employeeGoalWeight = (EditText) findViewById(R.id.txt_goalWeight);
            employeeAge = (EditText) findViewById(R.id.txt_Age);
            employeePhoneNum = (EditText) findViewById(R.id.txt_Phone);
            employeeAddress = (EditText) findViewById(R.id.txt_Address);

            //RADIO BUTTON
            Male = (RadioButton) findViewById(R.id.rbtn_Male);
            Female = (RadioButton) findViewById(R.id.rbtn_Female);
            Others = (RadioButton) findViewById(R.id.rbtn_Other);

            //BUTTON
            search = (Button) findViewById(R.id.btn_searchEmployeeId);
            submit = (Button) findViewById(R.id.btn_submit);

            //DATABASE
            dbContext = new DBHelper(this);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String employeeIdTxt = employeeId.getText().toString();
                    String employeeFullNameTxt = employeeFullName.getText().toString();
                    String employeeCurrentWeightTxt = employeeCurrentWeight.getText().toString();
                    String employeeHeightTxt = employeeHeight.getText().toString();
                    String employeeGoalWeightTxt = employeeGoalWeight.getText().toString();
                    String employeeAgeTxt = employeeAge.getText().toString();
                    String employeePhoneNumTxt = employeePhoneNum.getText().toString();
                    String employeeAddressTxt = employeeAddress.getText().toString();

                    String gender = ""; // Initialize gender as an empty string

                    if (Male.isChecked()) {
                        gender = "Male";
                    } else if (Female.isChecked()) {
                        gender = "Female";
                    } else if (Others.isChecked()) {
                        gender = "Others";
                    }

                    // Now, you can insert this data into the database
                    Boolean checkInsertData = dbContext.InsertData(employeeIdTxt, employeeFullNameTxt, gender, employeeCurrentWeightTxt, employeeHeightTxt, employeeGoalWeightTxt, employeeAgeTxt, employeePhoneNumTxt, employeeAddressTxt);


                    if(checkInsertData == true)
                    {
                        Toast.makeText(MainActivity.this, "Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Failed to Insert Data",Toast.LENGTH_SHORT).show();
                    }

                }
            });

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String employeeIdTxt = employeeId.getText().toString(); // Student ID
                    Cursor result = dbContext.SearchData(employeeIdTxt);

                    if (result.getCount() == 0) {
                        Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    } else {
                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("Employee ID: " + result.getString(0) + "\n");
                            buffer.append("Employee Full Name: " + result.getString(1) + "\n");
                            buffer.append("Employee Gender: " + result.getString(2) + "\n");
                            buffer.append("Employee Current Weight: " + result.getString(3) + "\n");
                            buffer.append("Employee Height: " + result.getString(4) + "\n");
                            buffer.append("Employee Goal Weight: " + result.getString(5) + "\n");
                            buffer.append("Employee Age: " + result.getString(6) + "\n");
                            buffer.append("Employee Phone #: " + result.getString(7) + "\n");
                            buffer.append("Employee Address: " + result.getString(8) + "\n");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Employee Data");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                }
            });





        }
}