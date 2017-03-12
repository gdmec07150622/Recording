package com.me.android.recording.database;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.me.android.recording.Record;
import com.me.android.recording.database.RecordDbSchema.RecrodTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2017/3/9.
 */

public class RecordCursorWrapper  extends CursorWrapper{
    public RecordCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Record getRecord(){
        String uuidString=getString(getColumnIndex(RecordDbSchema.RecrodTable.Cols.UUID));
        String title=getString(getColumnIndex(RecrodTable.Cols.TITLE));
        long date=getLong(getColumnIndex(RecrodTable.Cols.DATE));
        int isSolved=getInt(getColumnIndex(RecrodTable.Cols.SOLVED));

        Record record=new Record(UUID.fromString(uuidString));
        record.setmTitle(title);
        record.setmDate(new Date(date));
        record.setmSolved(isSolved!=0);
        return  record;



       // return  null;
    }
}
