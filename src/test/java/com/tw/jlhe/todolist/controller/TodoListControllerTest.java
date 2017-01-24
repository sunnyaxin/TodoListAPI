package com.tw.jlhe.todolist.controller;

import com.tw.jlhe.todolist.Application;
import com.tw.jlhe.todolist.Entity.TodoItem;
import com.tw.jlhe.todolist.model.MessageResponse;
import com.tw.jlhe.todolist.service.TodoListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TodoListControllerTest {

    private MockMvc mockMvc;
    @Mock
    @Autowired
    private TodoListService todoListServiceMock;
    @InjectMocks
    @Autowired
    private TodoListController todoListController;


    private TodoItem todoItem;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoListController).build();

        todoItem = new TodoItem();
        todoItem.setId(1);
        todoItem.setDone(false);
        todoItem.setText("test case");
        todoItem.setTimestamp("2011-01-21T11:33:21Z");

        Map<String,List<TodoItem>> todoListResponse = new HashMap<>();
        List<TodoItem> items = Arrays.asList(todoItem);
        todoListResponse.put("items",items);

        when(todoListServiceMock.getTodoItems()).thenReturn(todoListResponse);
        when(todoListServiceMock.createTodoItem(anyObject())).thenReturn(new MessageResponse("Create todo item is successful!",201, todoItem));
        when(todoListServiceMock.updateTodoItem(anyInt(),anyObject())).thenReturn(new MessageResponse("Update todo item is successful!", 200, todoItem));
        when(todoListServiceMock.deleteTodoItem(1)).thenReturn(new MessageResponse("Delete todo item is successful!",204));
        when(todoListServiceMock.deleteTodoItem(2)).thenReturn(new MessageResponse("Delete todo item is fail!",404));
    }

    @Test
    public void itemsShouldReturnTodoListWithJSON() throws Exception {
        mockMvc.perform(get("http://localhost:8080/todoitems"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("{\"items\":[{\"id\":1,\"text\":\"test case\",\"done\":false,\"timestamp\":\"2011-01-21T11:33:21Z\"}]}")));
    }

    @Test
    public void createItemShouldReturnIsCreatedWhenThisItemCreatedSuccefully() throws Exception {
        mockMvc.perform(post("http://localhost:8080/todoitems").contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"text\":\"test case\",\"done\":false,\"timestamp\":\"2015-01-21T11:33:21Z\"}"))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().string("{\"todoItem\":{\"id\":1,\"text\":\"test case\",\"done\":false,\"timestamp\":\"2011-01-21T11:33:21Z\"},\"message\":\"Create todo item is successful!\",\"status\":201}"));
    }

    @Test
    public void updateItemShouldReturnIsOkWhenThisItemUpdatedSuccefully() throws Exception {
        int id = 1;
        todoItem.setText("update test");
        todoItem.setDone(true);
        mockMvc.perform(put("http://localhost:8080/todoitems/{id}",id).contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"text\":\"test case\",\"done\":true,\"timestamp\":\"2015-01-21T11:33:21Z\"}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("{\"todoItem\":{\"id\":1,\"text\":\"update test\",\"done\":true,\"timestamp\":\"2011-01-21T11:33:21Z\"},\"message\":\"Update todo item is successful!./\",\"status\":200}"));
    }

    @Test
    public void deleteItemShouldReturnNoContentWhenThisItemIsExisting() throws Exception {
        int id = 1;
        mockMvc.perform(delete("http://localhost:8080/todoitems/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteItemShouldReturnNoFoundWhenThisItemIsNotExisting() throws Exception {
        int id = 2;
        mockMvc.perform(delete("http://localhost:8080/todoitems/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().string("{\"todoItem\":null,\"message\":\"Delete todo item is fail!\",\"status\":404}"));
    }
}