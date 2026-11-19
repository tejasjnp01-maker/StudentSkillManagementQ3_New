package com.kiet.studentskillmanagementq3.controller;
import com.kiet.studentskillmanagementq3.model.Student;
import com.kiet.studentskillmanagementq3.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/students")
public class StudentController {
 private final StudentService service;
 public StudentController(StudentService service){this.service=service;}
 @PostMapping public ResponseEntity<Student> add(@RequestBody Student s){return ResponseEntity.status(HttpStatus.CREATED).body(service.add(s));}
 @GetMapping public List<Student> all(){return service.all();}
 @GetMapping("/{id}") public Student byId(@PathVariable int id){return service.byId(id);}
 @PutMapping("/{id}") public Student update(@PathVariable int id,@RequestBody Student s){return service.update(id,s);}
 @DeleteMapping("/{id}") public ResponseEntity<Map<String,String>> delete(@PathVariable int id){
  service.delete(id); return ResponseEntity.ok(Map.of("message","Student deleted successfully."));
 }
 @GetMapping("/merit-list") public List<Student> merit(){return service.merit();}
}
