package com.example.notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NoticeManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(NoticeManagement1Application.class, args);
	}

}
