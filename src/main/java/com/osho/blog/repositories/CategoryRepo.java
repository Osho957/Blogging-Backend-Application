package com.osho.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osho.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	

}
