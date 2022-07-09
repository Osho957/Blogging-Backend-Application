package com.osho.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osho.blog.payloads.ApiResponse;
import com.osho.blog.payloads.UserDto;
import com.osho.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	//POST
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody  UserDto userDto){
	UserDto createdUserDto= this.userService.createUser(userDto);
	return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	//PUT
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
	UserDto updatedUser =this.userService.updateUser(userDto, uid);
	return ResponseEntity.ok(updatedUser); 
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	//GET
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid){
		return ResponseEntity.ok(this.userService.getUserById(uid));
	}
	
}
