package com.kluniversity.studentapp.controller;

import com.kluniversity.studentapp.model.Student;
import com.kluniversity.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .<ResponseEntity<?>>map(s -> ResponseEntity.ok(s))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.updateStudent(id, student);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id)
                ? ResponseEntity.ok("Student deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }
}
