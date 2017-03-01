package com.tw.jlhe.todolist.controller;

import com.tw.jlhe.todolist.entity.TodoItem;
import com.tw.jlhe.todolist.model.MessageResponse;
import com.tw.jlhe.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/todoitems")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map> items(){
        return ResponseEntity.ok(todoListService.getTodoItems());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@Valid @RequestBody TodoItem newTodoItem){
        newTodoItem.setTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()));
        MessageResponse messageResponse = todoListService.createTodoItem(newTodoItem);
        return ResponseEntity.status(messageResponse.getStatus()).body(messageResponse);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateItem(@PathVariable int id , @Valid @RequestBody TodoItem updateTodoItem) {
        updateTodoItem.setTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()));
        MessageResponse messageResponse = todoListService.updateTodoItem(id , updateTodoItem);
        return ResponseEntity.status(messageResponse.getStatus()).body(messageResponse);
    }

    @RequestMapping( value = "/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItem(@PathVariable int id){
        MessageResponse messageResponse = todoListService.deleteTodoItem(id);
        return ResponseEntity.status(messageResponse.getStatus()).body(messageResponse);
    }
}
