package com.ffeghali.starwarsapp.model;

public class TaskModel {
    public int uid;
    public int id;
    public String title;
    public boolean completed;

    public TaskModel(int uid, int id, String title, boolean completed) {
        this.uid = uid;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}