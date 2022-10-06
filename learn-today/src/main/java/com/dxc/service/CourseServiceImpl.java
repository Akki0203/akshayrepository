package com.dxc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.model.Course;
import com.dxc.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourserService {
	
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public List<Course> findAllCourse() {
		//findAll(Sort.by(Direction.ASC, field)
		return courseRepo.findAll();
	}

	@Override
	public Optional<Course> getCourseById(int id) {	
		return courseRepo.findById(id);
	}

	@Override
	public void deleteCourseById(String sid) {
		int id=Integer.parseInt(sid);
		courseRepo.deleteById(id);
	}

	@Override
	public Course saveNewCourse(Course p) {	
		return courseRepo.save(p);
	}

}
