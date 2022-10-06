package com.dxc.service;

import com.dxc.model.Trainer;
import com.dxc.model.UserDto;
import com.dxc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrainerService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trainer user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public Trainer save(UserDto user) {
		Trainer newUser = new Trainer();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}

	public Trainer updatePassword(String username, String password) {
		Trainer user = userDao.findByUsername(username);
		user.setPassword(bcryptEncoder.encode(password));
		return userDao.save(user);
		
	}
}