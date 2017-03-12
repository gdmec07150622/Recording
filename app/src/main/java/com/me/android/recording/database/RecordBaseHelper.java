package com.me.android.recording.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.me.android.recording.database.RecordDbSchema.RecrodTable;

/**
 * Created by admin on 2017/3/9.
 */

public class RecordBaseHelper extends SQLiteOpenHelper {
    private static  final int VERSION=1;
    private static final String DATABASE_NAME="recordBase.db";

    public RecordBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table "+ RecrodTable.NANE+"("+ "_id integer primary key autoincrement,"+ RecrodTable.Cols.UUID+","+ RecrodTable.Cols.TITLE+","+ RecrodTable.Cols.DATE+","+ RecrodTable.Cols.SOLVED+")"
//        db.execSQL("create table"+ RecrodTable.NANE+"("+ "_id integer primary key autoincrement,"+"a"+","+"a"+","+ "a"+","+ "a"+")"

       );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
