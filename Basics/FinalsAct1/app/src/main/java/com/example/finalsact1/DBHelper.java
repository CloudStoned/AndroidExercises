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
        DB.execSQL("CREATE TABLE EmployeesTable(employeeID TEXT primary key, employeeFullName TEXT, Gender TEXT, currentWeight TEXT, Height TEXT, goalWeight TEXT, Age TEXT, Phone TEXT, Address TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE if exists EmployeesTable");
    }

    public Boolean InsertData (String empId, String fullName,  String gender, String cWeight, String height, String gWeight, String age, String phone, String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentV = new ContentValues();
        contentV.put("employeeID", empId);
        contentV.put("employeeFullName",fullName);
        contentV.put("Gender",gender);
        contentV.put("currentWeight",cWeight);
        contentV.put("Height",height);
        contentV.put("goalWeight",gWeight);
        contentV.put("Age",age);
        contentV.put("Phone",phone);
        contentV.put("Address",address);

        long result = DB.insert("EmployeesTable",null,contentV);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor SearchData(String employeeId) {
        SQLiteDatabase DB = this.getWritableDatabase();
        String[] columns = {"employeeID","employeeFullName","Gender","currentWeight","Height","goalWeight","Age","Phone","Address"};
        String selection = "employeeId=?";
        String[] selectionArgs = {employeeId};

        Cursor cursor = DB.query("EmployeesTable", columns, selection, selectionArgs, null, null, null);
        return cursor;
    }







}
