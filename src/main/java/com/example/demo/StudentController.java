package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	 @Autowired
	    private StudentService studentService;

	    @GetMapping
	    public List<Student> getAllStudents() {
	        return studentService.getAllStudents();
	    }
	    @GetMapping("/{id}")
	    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	        return studentService.getStudentById(id)
	            .map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Student createStudent(@RequestBody Student student) {
	        return studentService.createStudent(student);
	    }
	    @PutMapping("/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
	        return studentService.updateStudent(id, studentDetails)
	            .map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id) {
	        boolean isDeleted = studentService.deleteStudent(id);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", isDeleted);
	        return isDeleted ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	    }
	}

