package com.tw.jlhe.todolist.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    @Column(nullable = false)
    @Size(max = 140 , message = "内容长度不得超过140字符")
    private String text;

    @Column(nullable = false)
    private boolean done;

    private String timestamp;

    public TodoItem() {
    }

    public TodoItem(String text) {
        this.text = text;
    }

    public TodoItem(String text, boolean done) {
        this.text = text;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
