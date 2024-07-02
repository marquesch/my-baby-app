package com.main.mybabyapp.data.model;

import java.util.Date;

public class Diaper implements ActivityItem{
    private int id;
    private Date time;
    private boolean pee;
    private boolean poop;

    public Diaper(int id, Date time, boolean pee, boolean poop) {
        this.id = id;
        this.time = time;
        this.pee = pee;
        this.poop = poop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean getPee() {
        return pee;
    }

    public void setPee(boolean pee) {
        this.pee = pee;
    }

    public boolean getPoop() {
        return poop;
    }
    public void setPoop(boolean poop) {
        this.poop = poop;
    }
}
