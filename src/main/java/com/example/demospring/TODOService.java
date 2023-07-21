package com.example.demospring;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TODOService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    TODO add(TODO todo){

        System.out.println(todo);


        String res = jdbcTemplate.queryForObject(
            "INSERT INTO DEMO_SCHEMA.TODOS(description, date_time, is_completed) "
            +" VALUES(?,?::TIMESTAMP,?) returning id", 
        new Object[]{todo.description,todo.dateTime,todo.isCompleted },
        new int[]{Types.VARCHAR,Types.VARCHAR,Types.BOOLEAN},
        String.class );

        
        System.out.println("id: ----->>>   "+res);


        todo.setId(Integer.parseInt(res));
        return todo;
    }

    boolean updateTODO(TODO todo){

        System.out.println(todo.id+"|"+todo.isCompleted);
        int res = jdbcTemplate.update("UPDATE DEMO_SCHEMA.TODOS SET IS_COMPLETED=? WHERE ID=?", todo.isCompleted, todo.id);
        if(res > 0){
            return true;
        }
        return false;
    }

    public List<TODO> getAllTodos() throws SQLException {
        List<TODO> todos = new ArrayList<>();
        List<Map<String, Object>> res = jdbcTemplate.queryForList("SELECT * FROM DEMO_SCHEMA.TODOS");

        res.forEach(e->{
            TODO todo=new TODO((int)e.get("id"), (String)e.get("description"), 
            (e.get("date_time")!=null?(String)e.get("date_time").toString():""), 
            (boolean)e.get("is_completed"));
            todos.add(todo);
        });

        return todos;
    }
   
 
}
