package com.cjufi.bookmanagement;

import com.cjufi.bookmanagement.configuration.RsaKeyProperties;
import com.cjufi.bookmanagement.model.User;
import com.cjufi.bookmanagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveUser(new User(null, "cjufi", "cjufi", "Filip", null, null));
			userService.makeAdmin("cjufi");
		};
	}
}



