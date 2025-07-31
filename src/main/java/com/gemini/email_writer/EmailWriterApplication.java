package com.gemini.email_writer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmailWriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailWriterApplication.class, args);
	}

}
