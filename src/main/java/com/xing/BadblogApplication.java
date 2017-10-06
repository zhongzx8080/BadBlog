package com.xing;

import com.xing.interceptor.HistoryInterceptor;
import com.xing.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class BadblogApplication extends WebMvcConfigurerAdapter {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Autowired
	private HistoryInterceptor historyInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(BadblogApplication.class, args);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
		registry.addInterceptor(historyInterceptor).addPathPatterns("/**");
	}


}
