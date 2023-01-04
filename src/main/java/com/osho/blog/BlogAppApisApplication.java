package com.osho.blog;

import java.util.List;

import com.osho.blog.exceptions.ApiException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.osho.blog.config.AppConstants;
import com.osho.blog.entities.Role;
import com.osho.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner  {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("1234"));
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");
			
			Role role2 = new Role();
			role2.setId(AppConstants.NORMAL_USER);
			role2.setName("ROLE_NORMAL");
			
		List<Role>roles=List.of(role1,role2);
		this.roleRepo.saveAll(roles);
			
		}catch(Exception e) {
			throw new ApiException("Error In Saving Roles");
		}
	}

}
