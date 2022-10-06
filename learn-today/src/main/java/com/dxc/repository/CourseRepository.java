package com.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
