package com.dxc.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dxc.model.Course;

import com.dxc.model.Student;
import com.dxc.service.CourserService;
import com.dxc.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourserService courseService;

	@RequestMapping(value = "/getAllCoursesorder", method = RequestMethod.GET)
	public ResponseEntity<Course> getAllCourses() throws Exception{
		try {
			List<Course> course = courseService.findAllCourse();
			course.sort((a, b) -> a.getStartDate().compareTo(b.getStartDate()));
			return new ResponseEntity(course, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("{\"statusCode\":4007,\"statusMessage\":\"Error handling decision\"}",
					HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping(value = "/deleteStudentEnrollment ")
	public ResponseEntity<?> deleteStudentEnrollment(@PathParam("cid") String sid) throws Exception {
		try {
			if (sid != null && sid.length() > 0) {
				studentService.deleteStudentById(sid);
			}
			return new ResponseEntity("{\"statusCode\":200,\"statusMessage\":\"deleted succefully....\"}",
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity("{\"statusCode\":4007,\"statusMessage\":\"Error handling decision\"}",
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/registerstudent", method = RequestMethod.POST)
	public ResponseEntity<?> registerProduct(@RequestBody Student payload) throws Exception{
		try {
			/*
			 * JSONObject object = new JSONObject(payload); Student student1=new Student();
			 * student1.setCourseId(object.getInt("courseId"));
			 * student1.setStudentId(object.getInt("StudentId"));
			 * student1.setCourses(object.getJSONObject("course"));
			 */
			
			Student student = studentService.saveNewEmployee(payload);
			return  ResponseEntity.ok(student);
		} catch (Exception e) {
			return new ResponseEntity("{\"statusCode\":4007,\"statusMessage\":\"Please check name or address\"}",
					HttpStatus.BAD_REQUEST);
		}

	}

}
