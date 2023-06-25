package com.example.demospring;

import java.util.ArrayList;
import java.util.List;


public class TODOService {

    static List<TODO> TODOList = new ArrayList<>();
    
    static TODO add(TODO todo){
        todo.setId(TODOList.size());
        TODOList.add(todo);
        return todo;
    }

    static List<TODO> getAllTodos(){
        return TODOList;
    }
}
