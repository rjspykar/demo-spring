package com.example.demospring;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TODOController {

  @Autowired
  private TODOService todoService;


  @GetMapping("/hello") @ResponseBody
  public String hello() {
    return "Hello, world!";
  }

  @GetMapping("/getTODOs") @ResponseBody
  public ResponseEntity<List<TODO>> getTODOs() throws SQLException {
    return ResponseEntity.ok(todoService.getAllTodos());
  }

  @PostMapping(value="/addTODO", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TODO> addTODO(@RequestBody TODO newTodo) throws ServerException {
    TODO todo = todoService.add(newTodo);
    if (todo == null) {
      throw new ServerException("Invalid Entry");
    } else {
      ResponseEntity<TODO> re = ResponseEntity.status(HttpStatus.CREATED).body(todo);
      return re;
    }
  }

  @PostMapping(value="/updateTODO", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> updateTODO(@RequestBody TODO newTodo) throws ServerException {
    Boolean todo = todoService.updateTODO(newTodo);
    ResponseEntity<Boolean> re = (todo ? 
          ResponseEntity.status(HttpStatus.CREATED).body(todo) : 
          ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(todo));
    return re;
  }

}
