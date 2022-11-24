package com.codebyte.userservice;

import com.codebyte.userservice.entity.User;
import com.codebyte.userservice.repository.UserRepository;
import com.codebyte.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class UserServiceApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (userRepository.count() < 1) {
			User user1 = new User();
			user1.setUserUlid("01GGTFP1AJA2C4P0HNM0R9YPDG");
			user1.setEmail("faisal@gmail.com");
			user1.setName("Faisal");
			userRepository.save(user1);

			User user2 = new User();
			user2.setUserUlid("01GGTFPBDTP8R9YHP1V9HNPSN2");
			user2.setEmail("saad@gmail.com");
			user2.setName("Saad");
			userRepository.save(user2);

			User user3 = new User();
			user3.setUserUlid("01GGWK8CADQC31446200AVN5SA");
			user3.setEmail("amir@gmail.com");
			user3.setName("Amir");
			userRepository.save(user3);

			User user4 = new User();
			user4.setUserUlid("01GGWK82V5K52SQGS1K1DTSN42");
			user4.setEmail("durrani@gmail.com");
			user4.setName("Durrani");
			userRepository.save(user4);
		}
	}

}
