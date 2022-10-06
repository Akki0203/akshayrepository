package com.dxc.controller;

import com.dxc.config.JwtTokenUtil;
import com.dxc.model.JwtRequest;
import com.dxc.model.JwtResponse;
import com.dxc.model.Trainer;
import com.dxc.model.UserDto;
import com.dxc.service.TrainerService;

import java.util.HashMap;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TrainerController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private TrainerService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		Trainer s=userDetailsService.save(user);
		HashMap<String,String> result=new HashMap<>();
		result.put("TrainerId", String.valueOf(s.getTrainerid()));
		result.put("username", s.getUsername());		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/updatepassword", method = RequestMethod.PUT)
	public ResponseEntity<?> saveUser(@PathParam("username") String username,@PathParam("password") String password) throws Exception {
		Trainer s=userDetailsService.updatePassword(username,password);
		HashMap<String,String> result=new HashMap<>();
		result.put("TrainerId", String.valueOf(s.getTrainerid()));
		result.put("username", s.getUsername());
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
