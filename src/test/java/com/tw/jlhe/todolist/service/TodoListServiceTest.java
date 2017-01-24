package com.tw.jlhe.todolist.service;

import com.tw.jlhe.todolist.Entity.TodoItem;
import com.tw.jlhe.todolist.model.MessageResponse;
import com.tw.jlhe.todolist.model.OptTodoItem;
import com.tw.jlhe.todolist.repository.TodoListRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class TodoListServiceTest {

    @Mock
    @Autowired
    private TodoListRepository todoListRepositoryMock;

    @InjectMocks
    @Autowired
    private TodoListService todoListService;

    private TodoItem todoItem;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        todoItem = new TodoItem();
        todoItem.setId(1);
        todoItem.setDone(false);
        todoItem.setText("test case");

        List<TodoItem> todoItems = Arrays.asList(todoItem);

        when(todoListRepositoryMock.findAll()).thenReturn(todoItems);
        when(todoListRepositoryMock.save((TodoItem) anyObject())).thenReturn(todoItem);
        when(todoListRepositoryMock.exists(1)).thenReturn(true);
        when(todoListRepositoryMock.exists(2)).thenReturn(false);
        when(todoListRepositoryMock.findOne(1)).thenReturn(todoItem);
    }

    @Test
    public void shouldReturnTodoItemsMapContainsItems() throws Exception {
        assertEquals( todoItem, todoListService.getTodoItems().get("items").get(0));
    }

    @Test
    public void shouldReturnMessageResponseWithSuccessfulAnd201StatusCodeAndItemWhenCreateItem() throws Exception {
        OptTodoItem createItem = new OptTodoItem("test case");
        MessageResponse messageResponse =  todoListService.createTodoItem(createItem);
        assertEquals("Create todo item is successful!",messageResponse.getMessage());
        assertEquals(201, messageResponse.getStatus());
        assertEquals(todoItem, messageResponse.getTodoItem());

    }

    @Test
    public void shouldReturnMessageResponseWithSuccessfulAnd200StatusCodeAndItemWhenUpdateItem() throws Exception {
        OptTodoItem updateItem = new OptTodoItem("test update", true);
        MessageResponse messageResponse = todoListService.updateTodoItem(1, updateItem);
        assertEquals("Update todo item is successful!", messageResponse.getMessage());
        assertEquals(200, messageResponse.getStatus());
        todoItem.setText("test update");
        todoItem.setDone(true);
        assertEquals(todoItem, messageResponse.getTodoItem());

    }

    @Test
    public void shouldReturnMessageResponseWithFailAnd404StatusCodeWhenItemNotFound() throws Exception {
        OptTodoItem updateItem = new OptTodoItem("test update", true);
        MessageResponse messageResponse = todoListService.updateTodoItem(2, updateItem);
        assertEquals("This todo item is not found!", messageResponse.getMessage());
        assertEquals(404, messageResponse.getStatus());
        assertEquals(null, messageResponse.getTodoItem());
    }

    @Test
    public void shouldReturnMessageResponseWith204StatusCodeWhenDeleteItem() throws Exception {
        int id = 1;
        MessageResponse messageResponse = todoListService.deleteTodoItem(id);
        assertEquals("Delete todo item option is successful!", messageResponse.getMessage());
        assertEquals( 204, messageResponse.getStatus());
    }

    @Test
    public void shouleReturnMessageResponseWith404StatusCodeWhenDeleteItemNotFound() throws Exception {
        int id = 2;
        MessageResponse messageResponse = todoListService.deleteTodoItem(id);
        assertEquals("This todo item is not found!", messageResponse.getMessage());
        assertEquals(404, messageResponse.getStatus());
    }
}