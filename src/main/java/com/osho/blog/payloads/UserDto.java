package com.osho.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.osho.blog.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min= 4 , message="Username must be min of 4 charcters")
	private String name;
	
	@Email(message="Email Address is not Valid")
	private String email;
	
	@NotEmpty 
	@Size(min= 4 , max =10, message="Password must be min of 4 charcters and max 10 characters")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}
