package com.bibliotheque.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BatchApplication {


	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
