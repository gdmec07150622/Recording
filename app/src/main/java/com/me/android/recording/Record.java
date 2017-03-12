package com.me.android.recording;

import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2017/3/4.
 */

public class Record {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    public Record(){
        //创建唯一标识符
        this(UUID.randomUUID());
       // mId = UUID.randomUUID();
      //  mDate=new Date();
    }
    public Record(UUID uuid){
        mId =uuid;
        mDate=new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
