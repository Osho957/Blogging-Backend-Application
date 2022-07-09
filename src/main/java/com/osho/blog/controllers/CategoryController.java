package com.osho.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osho.blog.payloads.ApiResponse;
import com.osho.blog.payloads.CategoryDto;
import com.osho.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody  CategoryDto categoryDto){
	CategoryDto createdCategoryDto= this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<>(createdCategoryDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer uid){
		CategoryDto updatedCategory =this.categoryService.updateCategory(categoryDto, uid);
	return ResponseEntity.ok(updatedCategory); 
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer uid){
		this.categoryService.deleteCategory(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}
	
	//GET
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Integer uid){
		return ResponseEntity.ok(this.categoryService.getCategoryById(uid));
	}

}
