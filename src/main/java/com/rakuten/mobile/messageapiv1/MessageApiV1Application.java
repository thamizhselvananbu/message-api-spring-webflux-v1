package com.rakuten.mobile.messageapiv1;

import com.rakuten.mobile.messageapiv1.model.UserModel;
import com.rakuten.mobile.messageapiv1.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Log4j2
public class MessageApiV1Application {

	public static void main(String[] args) {
		SpringApplication.run(MessageApiV1Application.class, args);
	}

	@Bean
	CommandLineRunner start(UserRepository userRepository) {
		return args -> {
			UserModel userModel = new UserModel("user", "user");
			userRepository.save(userModel).subscribe();

			UserModel userModel1 = new UserModel("admin", "admin");
			userRepository.save(userModel1).subscribe();

			userRepository.findAll().log().subscribe(userModels -> log.info("User: {}", userModels) );

		};
	}



}
