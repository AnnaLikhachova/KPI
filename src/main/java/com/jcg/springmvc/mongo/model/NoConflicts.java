package com.jcg.springmvc.mongo.model;

import java.util.Date;
import java.util.Objects;

public class NoConflicts {
    private String id;
    private String number;
    private String name;
    private int grow;
    private String aim;
    private Date timestamp;
    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrow() {
        return grow;
    }

    public void setGrow(int grow) {
        this.grow = grow;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void saveChanges(int counter){
        this.grow += counter - 90;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        NoConflicts that = (NoConflicts) o;
        return grow == that.grow &&
                Objects.equals(name, that.name) &&
                Objects.equals(aim, that.aim) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grow, aim, timestamp);
    }

    public void weMakeSomeConflicts(){
        System.out.println("Here exists conflict");
    }
}

