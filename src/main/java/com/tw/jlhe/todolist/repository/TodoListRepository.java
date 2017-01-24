package com.tw.jlhe.todolist.repository;

import com.tw.jlhe.todolist.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends CrudRepository<TodoItem , Integer>{
}
