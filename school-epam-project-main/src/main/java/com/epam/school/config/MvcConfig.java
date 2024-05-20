package com.epam.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	public void addViewControllers(ViewControllerRegistry registry) {
		//name of the new controller			name of the view
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/student").setViewName("students");
		registry.addViewController("/teacher").setViewName("teachers");
	}

}
