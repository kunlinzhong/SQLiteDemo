package com.comodin.sqlitetest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.comodin.tool.DBOpenHelper;
import com.comodin.tool.DBService_Course;
import com.comodin.tool.DatabaseContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBService_Course dbService_course = new DBService_Course(this);
        dbService_course.getWritableDatabase();
//        dbService_course.insertUser();
//        Cursor cursor = dbService_course.query("select * from t_user order by age DESC",null);
//        Cursor cursor = dbService_course.query("select * from t_user where age like '200%' order by age DESC",null);
//        Cursor cursor = dbService_course.query("select * from t_user where age like '200%' and age>2005 order by age DESC",null);
        Cursor cursor = dbService_course.query("select * from t_user where user_name like 'userName20%' and age like '___2%' and age>2005 order by age DESC",null);
        while (cursor.moveToNext()){
            Log.e("cursor",cursor.getString(cursor.getColumnIndex("age"))+"   "+ cursor.getString(cursor.getColumnIndex("user_name")));
        }
        cursor.close();
    }
}
