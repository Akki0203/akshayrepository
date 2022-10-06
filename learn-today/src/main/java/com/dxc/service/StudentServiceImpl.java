package com.dxc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.model.Student;
import com.dxc.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	
	 @Autowired
	 private StudentRepository studentRepo;
	@Override
	public Student saveNewEmployee(Student payload) {
		
		return studentRepo.save(payload);
	}
	@Override
	public void deleteStudentById(String sid) {
		int id=Integer.parseInt(sid);
		
		studentRepo.deleteById(id);
	}
	

}
