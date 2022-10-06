package com.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
