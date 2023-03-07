package com.project.boxinator;

import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.models.User;
import com.project.boxinator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class BoxinatorApplication implements ApplicationRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BoxinatorApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {



		User Milla = new User(1,"Milovan", "Glisovic", "abc@dfg.com", "kpr",
				"1999-09-15", "Sweden", 21231, "0743-23", TypeOfUser.Guest, new HashSet<>());

		userRepository.save(Milla);

	}
}
