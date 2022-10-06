package com.dxc.service;

import java.util.List;
import java.util.Optional;

import com.dxc.model.Course;

public interface CourserService {

	List<Course> findAllCourse();

	 Optional<Course> getCourseById(int id);

	void deleteCourseById(String sid);

	Course saveNewCourse(Course p);

}
