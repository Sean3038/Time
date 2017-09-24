package com.example.ffes.time.data;

/**
 * Created by Ffes on 2017/9/23.
 */

public class Record {
    Time current;
    Time total;
    int loop;

    Record(){}

public Record(Time current, Time total, int loop){
    this.current=current;
    this.total=total;
    this.loop=loop;
}
    public int getLoop() {
        return loop;
    }

    public Time getCurrent() {
        return current;
    }

    public Time getTotal() {
        return total;
    }

    public void setCurrent(Time current) {
        this.current = current;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public void setTotal(Time total) {
        this.total = total;
    }
}
