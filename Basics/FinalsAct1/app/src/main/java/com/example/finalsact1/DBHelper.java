package com.example.finalsact1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "StudentData.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE StudentData(studentId TEXT primary key, studentName TEXT, studentCourse TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE if exists StudentData");
    }

    public Boolean InsertData (String studId, String studName, String studCourse)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentV = new ContentValues();
        contentV.put("studentId", studId);
        contentV.put("studentName", studName);
        contentV.put("studentCourse", studCourse);

        long result = DB.insert("StudentData",null,contentV);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean UpdateData (String studentId, String newStudentName, String newStudentCourse)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentV = new ContentValues();
        contentV.put("studentName", newStudentName);
        contentV.put("studentCourse", newStudentCourse);

        long result = DB.update("StudentData", contentV, "studentId = ?", new String[]{studentId});
        DB.close();

        return result != -1;

    }

    public boolean DeleteData(String studentId) {
        SQLiteDatabase DB = this.getWritableDatabase();

        long result = DB.delete("StudentData", "studentId=?", new String[]{studentId});
        DB.close();
        return result != -1; // Return true if the deletion was successful
    }

    public Cursor SearchData(String studentId) {
        SQLiteDatabase DB = this.getWritableDatabase();
        String[] columns = {"studentId", "studentName", "studentCourse"};
        String selection = "studentId=?";
        String[] selectionArgs = {studentId};

        Cursor cursor = DB.query("StudentData", columns, selection, selectionArgs, null, null, null);
        return cursor;
    }







}
