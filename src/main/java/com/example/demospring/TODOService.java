package com.example.demospring;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TODOService {

    //private static final Logger log = LogManager.getLogger(TODOService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    TODO add(TODO todo){

        String res = jdbcTemplate.queryForObject(
                new StringBuilder().append("INSERT INTO DEMO_SCHEMA.TODOS(description, date_time, is_completed) ").append(" VALUES(?,?::TIMESTAMP,?) returning id").toString(),
        new Object[]{todo.description,todo.dateTime,todo.isCompleted },
        new int[]{Types.VARCHAR,Types.VARCHAR,Types.BOOLEAN},
        String.class );



        todo.setId(Integer.parseInt(res));
        return todo;
    }

    boolean updateTODO(TODO todo){
        int res = jdbcTemplate.update("UPDATE DEMO_SCHEMA.TODOS SET IS_COMPLETED=? WHERE ID=?", todo.isCompleted, todo.id);
        return res > 0;
    }



    public List<TODO> getAllTodos() throws SQLException {
        List<TODO> todos = new ArrayList<>();
        String query = "SELECT * FROM DEMO_SCHEMA.TODOS ORDER BY date_time DESC";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);

        for (Map<String, Object> row : results) {
            try {
                int id = (int) row.get("id");
                String description = (String) row.get("description");
                String dateTimeString = row.get("date_time") != null ? row.get("date_time").toString() : null;
                boolean isCompleted = (boolean) row.get("is_completed");


                TODO todo = new TODO(id, description, dateTimeString, isCompleted);
                todos.add(todo);
            } catch (ClassCastException | DateTimeParseException e) {
                System.err.println("Error parsing row: " + e.getMessage());
                // Optionally, handle the row parsing error (e.g., skip this row, or add a default TODO item)
            }
        }

        return todos;
    }
   
 
}
