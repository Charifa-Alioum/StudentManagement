package com.selftest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selftest.model.Student;
import com.selftest.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	@Autowired
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository=studentRepository;
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
	
	public Student updateStudent(Long id, Student student) {
		return studentRepository.findById(id)
				.map(existingStudent -> {
					existingStudent.setFirstName(student.getFirstName());
					existingStudent.setLastName(student.getLastName());
					
					return studentRepository.save(existingStudent);
				})
				.orElseThrow(() -> new RuntimeException("Student not found"));
	}
}
