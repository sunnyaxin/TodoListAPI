package com.tw.jlhe.todolist.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;

public class OptTodoItem {

    @Size(max = 140 , message = "内容最大长度不得超过140字符")
    private String text;
    private boolean done;
    @DateTimeFormat
    private String timestamp;

    public OptTodoItem() {
    }

    public OptTodoItem(String text) {
        this.text = text;
    }

    public OptTodoItem(String text, boolean done) {
        this.text = text;
        this.done = done;
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
