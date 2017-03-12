package com.me.android.recording;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.android.recording.database.RecordBaseHelper;
import com.me.android.recording.database.RecordCursorWrapper;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.me.android.recording.database.RecordDbSchema.*;

/**
 * Created by admin on 2017/3/4.
 */

public class RecordLab {
    private static RecordLab sRecordLab;
   // private List<Record> mRecords;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static RecordLab get(Context context){
        if(sRecordLab==null){
            sRecordLab=new RecordLab(context);
        }
        return  sRecordLab;
    }

    private RecordLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase=new RecordBaseHelper(mContext).getWritableDatabase();

       // mRecords=new ArrayList<>();
        /*
        for(int i=0;i<=100;i++){
            Record record=new Record();
            record.setmTitle("记录 #"+i);
            record.setmSolved(i % 2==0);
            mRecords.add(record);
        }
        */
    }
    public void addRecord(Record record){
      //  mRecords.add(record);
        ContentValues contentValues=getContentValues(record);
        mDatabase.insert(RecrodTable.NANE,null,contentValues);
    }


    public  List<Record>getRecords(){
      //  return mRecords;
      //  return new ArrayList<>();
        List<Record>recordList=new ArrayList<>();
        RecordCursorWrapper currsorWrapper=queryRecords(null,null);
        try{
            currsorWrapper.moveToFirst();
            while(!currsorWrapper.isAfterLast()){
                recordList.add(currsorWrapper.getRecord());
                currsorWrapper.moveToNext();
            }
        }finally {
            currsorWrapper.close();
        }
        return  recordList;
    }
    public Record getRecord(UUID id){
        /*
        for(Record record:mRecords){
            if(record.getmId().equals(id)){
                return record;
            }
        }
        */
      //  return null;
        RecordCursorWrapper cursorWrapper=queryRecords(
                RecrodTable.Cols.UUID+"=?",
                new String[]{id.toString()}
 );
        try{
            if(cursorWrapper.getCount()==0){
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getRecord();
        }finally {
            cursorWrapper.close();
        }

    }

    public void updateRecord(Record record){
        String uuidString=record.getmId().toString();
        ContentValues contentValues=getContentValues(record);
        mDatabase.update(RecrodTable.NANE,contentValues,RecrodTable.Cols.UUID+"=?",new String[]{uuidString});
    }



    private static ContentValues getContentValues(Record record){
        ContentValues contentValues=new ContentValues();
        contentValues.put(RecrodTable.Cols.UUID,record.getmId().toString());
        contentValues.put(RecrodTable.Cols.TITLE,record.getmTitle());
        contentValues.put(RecrodTable.Cols.DATE,record.getmDate().getTime());
        contentValues.put(RecrodTable.Cols.SOLVED,record.ismSolved()?1:0);
        return contentValues;
    }
    
    //private Cursor queryRecords(String whereClause,String[]whereArgs){
    private RecordCursorWrapper queryRecords(String whereClause,String[] whereArgs){
        Cursor cursor=mDatabase.query(
                RecrodTable.NANE,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
       //  return  cursor;
        return  new RecordCursorWrapper(cursor);
        }
    }
    
