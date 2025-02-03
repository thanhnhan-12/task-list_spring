package com.mvc.task_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Task List API", version = "1.0.0", description = "Example Project Spring Boot", license = @License(name = "thanhnhan-12")))
public class TaskListApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskListApplication.class, args);
	}
}