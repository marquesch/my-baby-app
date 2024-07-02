package com.main.mybabyapp.data.model;

import java.util.Date;

public class BreastFeed implements ActivityItem{
    private int id;
    private int length;
    private Date time;

    public BreastFeed(int id, int length, Date time) {
        this.id = id;
        this.length = length;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
