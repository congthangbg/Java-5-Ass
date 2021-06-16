package com.vn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vn.interceptors.Authentcate;
import com.vn.interceptors.UserAuthentcate;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
	@Autowired
	private Authentcate authenInterceptor;
	
	@Autowired
    private UserAuthentcate userAuthentcate;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenInterceptor)
			.addPathPatterns("/admin/**", "/users/**")
			.excludePathPatterns("/login", "/register");
		
			registry.addInterceptor(userAuthentcate)
	        .addPathPatterns("/admin/**")
	        .excludePathPatterns("/user/**");
	}
}
