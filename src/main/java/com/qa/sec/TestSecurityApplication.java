package com.qa.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.sec.persistence.repo.UserRepo;

@SpringBootApplication
public class TestSecurityApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(TestSecurityApplication.class, args);
		System.out.println(ac.getBean(UserRepo.class).findAll());
	}

}
