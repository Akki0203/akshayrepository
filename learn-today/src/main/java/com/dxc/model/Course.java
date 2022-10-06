package com.dxc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Course {
	@Id
	@GeneratedValue

	private int courseId;
	@Size(min = 5, message = "Name should have atleast 5 characters")
	private String title;
	private float fees;
	private String description;
	private String Trainer;
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	public int getCid() {
		return courseId;
	}

	public void setCid(int cid) {
		this.courseId = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrainer() {
		return Trainer;
	}

	public void setTrainer(String trainer) {
		Trainer = trainer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
