package com.educpo.ip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IpRestApi {

	public static void main(String[] args) {
		SpringApplication.run(IpRestApi.class, args);
	}

}
