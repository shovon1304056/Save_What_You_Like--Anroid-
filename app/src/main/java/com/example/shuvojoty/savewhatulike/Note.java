package com.example.shuvojoty.savewhatulike;


import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Note implements Serializable {
    private long  mDateTime;
    private String mTitle;
    private  String mContent;


    public Note(long mDateTime, String mTitle,String mContent ) {
        this.mContent = mContent;
        this.mTitle = mTitle;
        this.mDateTime = mDateTime;
    }

    public void setmDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContent() {
        return mContent;

    }

    //date will return  in a string format


    public String getDateTimeFormatted(Context context)
    {
        SimpleDateFormat sdf;
        sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return  sdf.format(new Date(mDateTime));
    }

}
