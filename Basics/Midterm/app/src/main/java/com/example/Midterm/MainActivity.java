package com.example.Midterm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.midterm_bagtas.R;

public class MainActivity extends AppCompatActivity {

    Button computeButton,closeButton; // BUTTONS
    EditText employeeId, basicPay, taxAllowance ,deductions;
    CheckBox vBasic, _Python, cSharp, jVa, cPlus, jScript, cb_Others;
    RadioButton Permanent, Contract, Temporary;
    String answer = "", jobTitle = "",skills = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEXT VIEWS INPUT
        employeeId = (EditText) findViewById(R.id.txt_employeeId);
        basicPay = (EditText) findViewById(R.id.txt_BasicPay);
        taxAllowance = (EditText) findViewById(R.id.txt_TaxAllowance);
        deductions = (EditText) findViewById(R.id.txt_Deduction);

        //CHECKBOXES
        vBasic = (CheckBox) findViewById(R.id.CBOX_vBasic);
        _Python = (CheckBox) findViewById(R.id.CBOX_pytHon);
        cSharp = (CheckBox) findViewById(R.id.CBOX_cSharp);
        jVa = (CheckBox) findViewById(R.id.CBOX_jVa);
        cPlus = (CheckBox) findViewById(R.id.CBOX_cPlus);
        jScript = (CheckBox) findViewById(R.id.CBOX_jScript);
        cb_Others = (CheckBox) findViewById(R.id.CBOX_others);

        //RADIOBUTTON
        Permanent = (RadioButton) findViewById(R.id.RBTN_Permanent);
        Contract = (RadioButton) findViewById(R.id.RBTN_Contract);
        Temporary = (RadioButton) findViewById(R.id.RBTN_Temporary);

        //BUTTON
        computeButton = (Button) findViewById(R.id.btn_Compute);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int _basicPay = Integer.parseInt(basicPay.getText().toString());
                    int _taxAllowance = Integer.parseInt(taxAllowance.getText().toString());
                    int _deduction   = Integer.parseInt(deductions.getText().toString());

                    int grossPay = _basicPay + _taxAllowance;
                    int netPay = grossPay - _deduction;

                    String message = "Employee ID: " + employeeId.getText().toString() + "\n";

                    if (vBasic.isChecked())
                    {
                        skills = "Visual Basic";
                    }
                    else if (_Python.isChecked())
                    {
                        skills = "Python";
                    }
                    else if (cSharp.isChecked())
                    {
                        skills = "C#";
                    }

                    else if (jVa.isChecked())
                    {
                        skills = "Java";
                    }

                    else if (cPlus.isChecked())
                    {
                        skills = "C++";
                    }

                    //APPEND TO GENDER MESSAGE
                    message += "skills: " + skills + "\n";

                    // Append selected certifications to the message
                    message += "Job Title : ";

                    if (Permanent.isChecked())
                    {
                        message += "Permanent ";
                    }
                    if (Contract.isChecked())
                    {
                        message += "Contract ";
                    }
                    if (Temporary.isChecked())
                    {
                        message += "Temporary ";
                    }


                    message += "Net Pay:" + netPay + "\n";

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