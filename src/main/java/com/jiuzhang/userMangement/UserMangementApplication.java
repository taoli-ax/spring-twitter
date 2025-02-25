package com.jiuzhang.userMangement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "Demo : User Management API", version = "v1", description = "API documentation for managing users"))
@SpringBootApplication
public class UserMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMangementApplication.class, args);
	}

}
