package com.selftest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selftest.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
