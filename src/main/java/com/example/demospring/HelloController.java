package com.example.demospring;

import java.rmi.ServerException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


  @GetMapping("/hello")
  @ResponseBody
  public String hello() {
    return "Hello, world!";
  }

  @GetMapping("/getTODOs")
  @ResponseBody
  public ResponseEntity<List<TODO>> getTODOs() {
    return ResponseEntity.ok(TODOService.getAllTodos());
  }

  @PostMapping(value="/addTODO", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TODO> addTODO(@RequestBody TODO newTodo) throws ServerException {

    TODO todo = TODOService.add(newTodo);

    System.out.println("------>>>>  "+todo);

    

    if (todo == null) {
      throw new ServerException("Invalid Entry");
    } else {
      ResponseEntity<TODO> re = ResponseEntity.status(HttpStatus.CREATED).body(todo);
      System.out.println(re);
      return re;
    }
  }

}
