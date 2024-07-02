package com.main.mybabyapp.data.model;

import java.util.Date;

public class BottleFeed implements ActivityItem{
    private int id;
    private int quantity;
    private Date time;

    public BottleFeed(int id, int quantity, Date time) {
        this.id = id;
        this.quantity = quantity;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
