package com.example.activity5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button send,close,cancel;
    EditText txtName;
    CheckBox eng,mat,fil,sci;
    String ans = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtS);
        eng = findViewById(R.id.chkEnglish);
        mat = findViewById(R.id.chkMath);
        fil = findViewById(R.id.chkFilipino);
        sci = findViewById(R.id.chkScience);

        close = findViewById(R.id.btnClose);
        cancel = findViewById(R.id.btnCancel);
        send = findViewById(R.id.btnSend);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to exit?").
                        setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish(); }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();     }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}