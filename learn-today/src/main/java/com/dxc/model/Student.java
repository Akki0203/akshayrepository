package com.dxc.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Student {
	
	@Id
	@GeneratedValue(generator = "EId")
	@GenericGenerator(name="EId", strategy = "com.dxc.util.EnrollementIdGenerator")
	private int enrollementId;
	private int StudentId;
	private int courseId;
	@OneToMany(mappedBy="student")  
	private List<Course> courses;
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public int getEnrollementId() {
		return enrollementId;
	}
	public void setEnrollementId(int enrollementId) {
		this.enrollementId = enrollementId;
	}
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	

}
