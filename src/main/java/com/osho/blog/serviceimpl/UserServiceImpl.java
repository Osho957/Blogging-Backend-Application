package com.osho.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.osho.blog.config.AppConstants;
import com.osho.blog.entities.Role;
import com.osho.blog.entities.User;
import com.osho.blog.exceptions.ResourceNotFoundException;
import com.osho.blog.payloads.UserDto;
import com.osho.blog.repositories.RoleRepo;
import com.osho.blog.repositories.UserRepo;
import com.osho.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	//create user
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User savedUser =this.userRepo.save(user);
		return this.userToDto(savedUser);
	}
	
	//update user
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User savedUser =this.userRepo.save(user);
		return this.userToDto(savedUser);
	}
	
	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		 List<User> users =	this.userRepo.findAll();
		 List<UserDto>userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		 User user = this.dtoToUser(userDto);
		 user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role =  this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
	   User newUser =	this.userRepo.save(user);
		return this.userToDto(newUser);
	}

}
