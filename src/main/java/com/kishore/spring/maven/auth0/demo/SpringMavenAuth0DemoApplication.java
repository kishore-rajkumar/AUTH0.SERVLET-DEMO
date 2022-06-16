package com.kishore.spring.maven.auth0.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication//(exclude = {ErrorMvcAutoConfiguration.class})
public class SpringMavenAuth0DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMavenAuth0DemoApplication.class, args);
	}

}
