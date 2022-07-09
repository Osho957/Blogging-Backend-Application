package com.osho.blog.entities;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
	private String username;
	
	private String password;
	

}
