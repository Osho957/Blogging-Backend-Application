package com.osho.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min= 4 , message="Category must be min of 4 charcters")
	private String categoryTitle;

	@NotEmpty
	private String categoryDescription;
}
