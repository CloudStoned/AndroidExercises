package com.example.quiz1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button computeButton,closeButton; // BUTTONS
    EditText employeeId, ratesPerHour, hoursWork,deductions;
    CheckBox cyberSecurity, cPlus, _Python, cb_Others;
    RadioButton Male,Female,rb_Others;
    String answer = "", gender = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEXT VIEWS INPUT
        employeeId = (EditText) findViewById(R.id.txt_employeeId);
        ratesPerHour = (EditText) findViewById(R.id.txt_RatePerHour);
        hoursWork = (EditText) findViewById(R.id.txt_HoursWorked);
        deductions = (EditText) findViewById(R.id.txt_Deduction);

        //CHECKBOXES
        cyberSecurity = (CheckBox) findViewById(R.id.CBOX_CyberSec);
        cPlus = (CheckBox) findViewById(R.id.CBOX_Cplus);
        _Python = (CheckBox) findViewById(R.id.CBOX_Python);
        cb_Others = (CheckBox) findViewById(R.id.CBOX_Others);

        //RADIOBUTTON
        Male = (RadioButton) findViewById(R.id.RBTN_Male);
        Female = (RadioButton) findViewById(R.id.RBTN_Female);
        rb_Others = (RadioButton) findViewById(R.id.RBTN_Others);

        //BUTTON
        closeButton = (Button) findViewById(R.id.btn_Close);
        computeButton = (Button) findViewById(R.id.btn_Compute);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to exit?").setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int _ratePerHour = Integer.parseInt(ratesPerHour.getText().toString());
                    int _hoursWorked = Integer.parseInt(hoursWork.getText().toString());
                    int _deduction   = Integer.parseInt(deductions.getText().toString());

                    int grossSalary = _ratePerHour * _hoursWorked; //Get first the grossSalary
                    int netSalary = grossSalary - _deduction;

                    String message = "Employee ID: " + employeeId.getText().toString() + "\n";

                    //CHECKBOX
                    if (Male.isChecked())
                    {
                        gender = "Male";
                    }
                    else if (Female.isChecked())
                    {
                        gender = "Female";
                    }
                    else if (rb_Others.isChecked())
                    {
                        gender = "Other";
                    }

                    //APPEND TO GENDER MESSAGE
                    message += "Gender: " + gender + "\n";

                    // Append selected certifications to the message
                    message += "Certification : ";

                    if (cyberSecurity.isChecked())
                    {
                        message += "Cybersecurity ";
                    }
                    if (cPlus.isChecked())
                    {
                        message += "C++ ";
                    }
                    if (_Python.isChecked())
                    {
                        message += "Python ";
                    }
                    if (cb_Others.isChecked())
                    {
                        message += "Others ";
                    }


                    message += "Rates Per Hour: " + _ratePerHour + "\n";
                    message += "Hours Work: " + _hoursWorked + "\n";
                    message += "Net Salary:" + netSalary + "\n";

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Employee Information")
                            .setMessage(message)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    alertDialog.show();
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(), "Please enter valid numeric values.", Toast.LENGTH_LONG).show();
                }
            }
        });






    }
}