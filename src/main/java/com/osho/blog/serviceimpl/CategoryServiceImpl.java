package com.osho.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osho.blog.entities.Category;
import com.osho.blog.exceptions.ResourceNotFoundException;
import com.osho.blog.payloads.CategoryDto;
import com.osho.blog.repositories.CategoryRepo;
import com.osho.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
  
	@Autowired
	private ModelMapper modelMapper;
 
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category =this.dtoToCategory(categoryDto);
		Category savedCategory =this.categoryRepo.save(category);
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));	
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category savedCategory = this.categoryRepo.save(category);
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));	
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> list = this.categoryRepo.findAll();
		List<CategoryDto> categoryDto = list.stream().map(category->this.categoryToDto(category)).collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));	
		this.categoryRepo.delete(category);	
	}
	
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	private CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
