package com.osho.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.osho.blog.entities.User;
import com.osho.blog.exceptions.ResourceNotFoundException;
import com.osho.blog.repositories.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	  User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email : "+ username, 0l));
	
	  return user;
	}

}
