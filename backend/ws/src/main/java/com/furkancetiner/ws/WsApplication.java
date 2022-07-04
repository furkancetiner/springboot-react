package com.furkancetiner.ws;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.furkancetiner.ws.model.User;
import com.furkancetiner.ws.service.UserService;

@SpringBootApplication
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}
	
	
	/*@Bean
	CommandLineRunner createInitialUsers(UserService userService) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				User user = new User();
				String username="";
				Random rnd = new Random();
				for(int i=0;i<5;i++) {
					char a = (char) (rnd.nextInt(26)+97);
					username+=a;
				}
				
				user.setUsername(username);
				user.setDisplayName("F"+username+"12345");
				user.setPassword("F"+username+"12345");
				userService.createUser(user);
				System.out.print(user);
			}
		};
	}*/

}
