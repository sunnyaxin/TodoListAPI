package com.tw.jlhe.todolist.service;

import com.tw.jlhe.todolist.Entity.TodoItem;
import com.tw.jlhe.todolist.model.MessageResponse;
import com.tw.jlhe.todolist.model.OptTodoItem;
import com.tw.jlhe.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    public Map<String,List<TodoItem>> getTodoItems() {
        Map<String,List<TodoItem>> todoListResponse = new HashMap<>();
        List<TodoItem> items = new ArrayList<>();
        for(TodoItem item : todoListRepository.findAll()){
            items.add(item);
        }
        todoListResponse.put("items",items);
        return todoListResponse;
    }


    public MessageResponse createTodoItem(OptTodoItem newTodoItem) {
        TodoItem todoItem = new TodoItem();
        todoItem.setText(newTodoItem.getText());
        todoItem.setTimestamp(newTodoItem.getTimestamp());
        todoItem.setDone(false);
        return new MessageResponse("Create todo item successful.",201,todoListRepository.save(todoItem));
    }

    public MessageResponse updateTodoItem(int id , OptTodoItem updateTodoItem) {
        if(todoListRepository.exists(id)) {
            TodoItem item = todoListRepository.findOne(id);
            item.setDone(updateTodoItem.isDone());
            item.setText(updateTodoItem.getText());
            item.setTimestamp(updateTodoItem.getTimestamp());
            return new MessageResponse("Update todo item is successful!",200,todoListRepository.save(item));
        }else{
            return new MessageResponse("This todo item is not found!",404);
        }
    }

    public MessageResponse deleteTodoItem(int id) {
        if(todoListRepository.exists(id)){
            todoListRepository.delete(id);
            return new MessageResponse("Delete todo item option is successful",204);
        }else{
            return new MessageResponse("This todo item is not found!",404);
        }

    }


}
