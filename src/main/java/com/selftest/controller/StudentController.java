package com.selftest.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selftest.model.Student;
import com.selftest.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_read:students')")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_write:students')")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_write:students')")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_delete:students')")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}
}
