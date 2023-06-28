package com.example.markapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelp extends SQLiteOpenHelper {
    //创建数据库
    public DataBaseHelp(Context context) {
        super(context, "content3_db", null, 1);
    }
    //创建表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table marklist(id integer primary key autoincrement,mesTitle varchar(20),mesTime varchar(20),mesContent varchar(10000),type integer)";
        String createUserList="create table userlist(id integer primary key autoincrement,username varchar(20),password varchar(20))";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(createUserList);

        Log.i("TAG", "执行了没报错" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
