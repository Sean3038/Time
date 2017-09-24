package com.example.ffes.time.data;

/**
 * Created by Ffes on 2017/9/23.
 */

public class Time {
    int minute;
    int second;
    int minsecond;

    public Time(){}

    public Time(int minute,int second,int minsecond){
        this.minute=minute;
        this.second=second;
        this.minsecond=minsecond;
    }
    public void setMinsecond(int minsecond) {
        this.minsecond = minsecond;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinsecond() {
        return minsecond;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("%02d",minute)+":"+String.format("%02d",second)+"."+String.format("%02d",minsecond) ;
    }
}
