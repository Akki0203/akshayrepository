package com.dxc.service;

import com.dxc.model.Student;

public interface StudentService {

	Student saveNewEmployee(Student payload);

	void deleteStudentById(String sid);

}
