package com.osho.blog.payloads;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	@NotEmpty
	@Size(min= 4 , message="Title must be min of 4 charcters")
	private String title;
	@NotEmpty
	private String content;
	
	private String imageName;

	private Date addDate;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments = new TreeSet<>();
	

}
