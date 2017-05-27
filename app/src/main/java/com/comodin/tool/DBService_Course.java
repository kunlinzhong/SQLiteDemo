package com.comodin.tool;

/**
 * Created by zhong on 2017/5/27.
 */

import java.io.File;
import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class DBService_Course extends SQLiteOpenHelper  {
    private final static int DATABASE_VERSION = 1;
//    public static final String dbPath = android.os.Environment
//            .getExternalStorageDirectory().getAbsolutePath() + "CourseDB";
public static final String dbPath = "/storage/emulated/0/comodin/";
    public static final String DB_NAME = dbPath + "/" + "comodin.db";

    private File dbf = null;

    String CREATE_TABLE_GT ="CREATE TABLE IF NOT EXISTS t_test (id integer primary key autoincrement, "
            + "user_name text not null,"
            + "vehicle_id text not null,"
            + "upload_time_stamp text not null,"
            + "longitude text not null,"
            + "latitude text not null,"
            + "upload_status text not null)";

    String CREATE_TABLE_USER ="CREATE TABLE IF NOT EXISTS t_user (id integer primary key autoincrement, "
            + "user_name text not null,"
            +"age test not null,"
            + "status text not null)";


    public static final String DROP_TABLE_GT = "DROP TABLE IF  EXISTS t_test ";
    public DBService_Course(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
//        db.execSQL(CREATE_TABLE_GT);
        db.execSQL(CREATE_TABLE_USER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(DROP_TABLE_GT);
        onCreate(db);
    }
    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        File dbp = new File(dbPath);
        dbf = new File(DB_NAME);
        if (!dbp.exists()) {
            dbp.mkdir();
        }
        // 数据库文件是否创建成功
        boolean isFileCreateSuccess = false;
        if (!dbf.exists()) {
            try {
                isFileCreateSuccess = dbf.createNewFile();
                if(isFileCreateSuccess)
                {
                    SQLiteDatabase.openOrCreateDatabase(dbf, null).execSQL(CREATE_TABLE_GT);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            isFileCreateSuccess = true;
        }
        if (isFileCreateSuccess) {
            return SQLiteDatabase.openOrCreateDatabase(dbf, null);
        }
        else {
            return null;
        }
    }

    public void close()
    {
        if(dbf.exists())
        {
            SQLiteDatabase.openOrCreateDatabase(dbf, null).close();
        }

    }

    // 执行insert、update、delete等SQL语句
    public void execSQL(String sql, Object[] args) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql, args);
    }
    public void execSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }
    // 执行select语句
    public Cursor query(String sql, String[] args) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);
        return cursor;
    }
    public Cursor query(String sql_list_table) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 插入一条票据信息
     * @return
     */
    public long insert() {
        SQLiteDatabase db = this.getWritableDatabase();
//        Log.e("insertComprobante","insertComprobante start");
        ContentValues initialValues = new ContentValues();
//        initialValues.put("id","1");
        initialValues.put("user_name","12");
        initialValues.put("vehicle_id","123");
        initialValues.put("upload_time_stamp","1234");
        initialValues.put("longitude","12345");
        initialValues.put("latitude","123456");
        initialValues.put("upload_status","1234567");
        Long re = db.insert("t_test", null, initialValues);
        Log.e("t_test re",""+re);
         return re;
    }

    public void  insertUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i=0;i<10000;i++){
            //        Log.e("insertComprobante","insertComprobante start");
            ContentValues initialValues = new ContentValues();
            initialValues.put("user_name","userName"+i);
            initialValues.put("age",i);
            initialValues.put("status","Y");
            Long re = db.insert("t_user", null, initialValues);
            Log.e("t_user-->"+i,""+re);
        }
    }
}