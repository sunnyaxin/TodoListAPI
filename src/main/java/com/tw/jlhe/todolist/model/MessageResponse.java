package com.tw.jlhe.todolist.model;

import com.tw.jlhe.todolist.entity.TodoItem;

public class MessageResponse {

    private TodoItem todoItem;
    private String message;

    private int status;

//    public MessageResponse() {
//    }

    public MessageResponse(String message, int status){
        this.message = message;
        this.status = status;
    }

    public MessageResponse(String message, int status, TodoItem todoItem ) {
        this.message = message;
        this.status = status;
        this.todoItem = todoItem;
    }

    public String getMessage() {
        return message;
    }

//    public void setMessage(String message) {
//        this.message = message;
//    }

    public int getStatus() {
        return status;
    }

//    public void setStatus(int status) {
//        this.status = status;
//    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

//    public void setTodoItem(TodoItem todoItem) {
//        this.todoItem = todoItem;
//    }
}
