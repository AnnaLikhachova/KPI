package com.jcg.springmvc.mongo.model;

public class NoConflicts {
    private String id;
    private String number;
    private String name;
    private int grow;

    public NoConflicts(String id, String number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void saveChanges(int counter){
        this.grow += counter;
    }
}
