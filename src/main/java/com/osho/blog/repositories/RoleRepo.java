package com.osho.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osho.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {


	
}
