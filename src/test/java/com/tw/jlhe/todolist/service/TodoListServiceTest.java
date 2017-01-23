package com.tw.jlhe.todolist.service;


import com.tw.jlhe.todolist.Entity.TodoItem;
import com.tw.jlhe.todolist.repository.TodoListRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TodoListServiceTest {

    @Mock
    private TodoListRepository todoListRepositoryMock;

    @InjectMocks
    private TodoListService todoListService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        TodoItem item = new TodoItem();
        item.setId(1);
        item.setText("test");
        item.setDone(false);
        item.setTimestamp("2011-01-21T11:33:21Z");
        List<TodoItem> items = new ArrayList();
        items.add(item);
        when(todoListRepositoryMock.findAll()).thenReturn(items);
    }

    @Test
    public void shouldReturnTheItemsList() throws Exception{
        assertEquals(todoListRepositoryMock.findAll(),todoListService.getTodoItems().get("items"));
    }

    @Test
    public void shouldReturnThe() throws Exception {

    }
}