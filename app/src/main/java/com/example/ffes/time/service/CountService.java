package com.example.ffes.time.service;


import android.os.Message;
import android.os.Handler;
import android.util.Log;

import com.example.ffes.time.data.Record;
import com.example.ffes.time.data.Time;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ffes on 2017/9/23.
 */

public class CountService {
    public static CountService countService;
    Timer timer;
    boolean isStart;
    boolean isRecord;

    int min=0,sec=0,tsec=0;

    int loop=0;
    int s_min=0,s_sec=0,s_tsec=0;
    OnChangeTime onChangeTime;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    if(onChangeTime!=null){
                        Time time=new Time(min,sec,tsec);
                        Time s_time=new Time(s_min,s_sec,s_tsec);
                        onChangeTime.onChange(time);
                        onChangeTime.onSingleChange(s_time);
                        if(isRecord){
                            Record record=new Record(s_time,time,loop+1);
                            onChangeTime.onUpdateRecord(record);
                        }
                    }
                    break;
                case 2:
                    if(onChangeTime!=null){
                        Time time=new Time(min,sec,tsec);
                        onChangeTime.onChange(time);
                        onChangeTime.onSingleChange(time);
                    }
                    break;
                case 3:
                    if (onChangeTime !=null) {
                        Time time=new Time(min,sec,tsec);
                        Time s_time=new Time(s_min,s_sec,s_tsec);
                        if(!isRecord) {
                            Record record = new Record(s_time, time, loop+1);
                            onChangeTime.onRecord(record);
                        }
                            Record n_record = new Record(s_time, time, loop++);
                            onChangeTime.onRecord(n_record);

                        isRecord = true;
                    }
            }
        }
    };

    private CountService(){
    }

    public static CountService getService(){
        if(countService==null){
            countService=new CountService();
        }
        return countService;
    }

    public void startCount(){
        timer=new Timer();
        isStart=true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isStart) {
                    tsec++;
                    sec=sec+tsec/100;
                    min=min+sec/60;
                    tsec%=100;
                    sec%=60;

                    s_tsec++;
                    s_sec=s_sec+s_tsec/100;
                    s_min=s_min+s_sec/60;
                    s_tsec%=100;
                    s_sec%=60;

                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
            }
        },0,10);
    }

    public void stopCount(){
        isStart=false;
        isRecord=false;
        timer.cancel();
    }
    public void resetCount(){
        min=sec=tsec=s_min=s_sec=s_tsec=0;
        isRecord=false;
        isStart=false;
        if(onChangeTime!=null){
            loop=0;
            Message message=new Message();
            message.what=2;
            handler.sendMessage(message);
        }
    }

    public void singleCount(){
            if (onChangeTime != null) {
                Message message = new Message();
                message.what = 3;
                handler.sendMessage(message);
            }
        s_min=s_sec=s_tsec=0;
    }

    public void setOnChangeTime(OnChangeTime onChangeTime) {
        this.onChangeTime = onChangeTime;
    }

    public interface OnChangeTime{
        void onChange(Time time);
        void onSingleChange(Time time);
        void onUpdateRecord(Record record);
        void onRecord(Record record);
    }
}
