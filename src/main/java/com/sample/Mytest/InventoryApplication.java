package com.sample.Mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class,args);
	}


}
