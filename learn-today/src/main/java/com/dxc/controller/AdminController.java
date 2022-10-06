package com.dxc.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dxc.model.Course;
import com.dxc.service.CourserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CourserService courseService;

	@RequestMapping(value = "/registercourse", method = RequestMethod.POST)
	public ResponseEntity registerProduct(@RequestBody Course cObj) throws Exception {
		try {
			Course course = courseService.saveNewCourse(cObj);
			return new ResponseEntity(course, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity("{\"statusCode\":4007,\"statusMessage\":\"Please check name or address\"}",
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/getAllCourses", method = RequestMethod.GET)
	public ResponseEntity<Course> getAllCourses() throws Exception{
		try {
			List<Course> course = courseService.findAllCourse();
			return new ResponseEntity(course, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity("{\"statusCode\":4007,\"statusMessage\":\"Error handling decision\"}",
					HttpStatus.BAD_REQUEST);

		}

	}

	@RequestMapping(value = "/getCourseById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Course> getCourseById(@PathVariable("id") String cid) throws Exception {
		try {
			
			if (cid == null)
				return new ResponseEntity("{\"statusCode\":400,\"statusMessage\":\"course id Should not Empty.....\"}",
						HttpStatus.BAD_REQUEST);
			if (cid != null) {
				int id = Integer.parseInt(cid);
				Optional<Course> course = courseService.getCourseById(id);
				return new ResponseEntity(course, HttpStatus.ACCEPTED);

			}

		} catch (Exception e) {
			return new ResponseEntity("{\"statusCode\":4007,\"statusMessage\":\"Searched Data not Found\"}",
					HttpStatus.NOT_FOUND);

		}
		return null;

	}

	@GetMapping(value = "/deleteCourseEnrollment/{Enrollmentid} ")
	public ResponseEntity deleteCourseEnrollment(@PathVariable("Enrollmentid") String sid) throws Exception{
		try {
			if (sid != null && sid.length() > 0) {
				courseService.deleteCourseById(sid);
			}
			return new ResponseEntity("{\"statusCode\":200,\"statusMessage\":\"deleted succefully....\"}",
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity("{\"statusCode\":4007,\"statusMessage\":\"Error handling decision\"}",
					HttpStatus.BAD_REQUEST);
		}

	}

}
