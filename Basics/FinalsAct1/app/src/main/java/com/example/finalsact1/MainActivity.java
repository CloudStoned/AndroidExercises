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
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

        String _studentId,_studentName,_studentCourse;
        EditText studentId, studentName, studentCourse;
        Button search, add, update,delete,close;

        DBHelper dbContext;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //TEXT VIEWS
            studentId = (EditText) findViewById(R.id.txt_studentNumber);
            studentName = (EditText) findViewById(R.id.txt_enterName);
            studentCourse = (EditText) findViewById(R.id.txt_enterCourse);

            //BUTTON
            search = (Button) findViewById(R.id.btn_searchStudentNumber);
            add = (Button) findViewById(R.id.btn_save);
            update = (Button) findViewById(R.id.btn_update);
            delete = (Button)findViewById(R.id.btn_delete);
            close = (Button) findViewById(R.id.btn_close);

            //DataBase
            dbContext = new DBHelper(this);

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String studentIdTxt = studentId.getText().toString(); // Student ID
                    Cursor result = dbContext.SearchData(studentIdTxt);

                    if (result.getCount() == 0) {
                        Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    } else {
                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("Student ID: " + result.getString(0) + "\n");
                            buffer.append("Student Name: " + result.getString(1) + "\n");
                            buffer.append("Student Course: " + result.getString(2) + "\n");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Student Data");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String studentIdTxt = studentId.getText().toString();
                    String studentNameTxt = studentName.getText().toString();
                    String studentCourseTxt = studentCourse.getText().toString();

                    Boolean checkInsertData = dbContext.InsertData(studentIdTxt,studentNameTxt,studentCourseTxt);

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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentIdTxt = studentId.getText().toString(); // Student ID
                String newStudentNameTxt = studentName.getText().toString(); // New student name
                String newStudentCourseTxt = studentCourse.getText().toString(); // New student course

                Boolean checkUpdateData = dbContext.UpdateData(studentIdTxt, newStudentNameTxt, newStudentCourseTxt);

                if (checkUpdateData) {
                    Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to Update Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentIdTxt = studentId.getText().toString(); // Student ID
                Boolean checkDeleteData = dbContext.DeleteData(studentIdTxt);

                if(checkDeleteData == true)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed to Delete Data",Toast.LENGTH_SHORT).show();

                }
            }
        });

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