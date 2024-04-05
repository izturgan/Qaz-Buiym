package com.qb.ecommerce;

import com.qb.ecommerce.auth.AuthenticationService;
import com.qb.ecommerce.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.qb.ecommerce.model.enums.Role.*;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();

			var user1 = RegisterRequest.builder()
					.firstname("Laura")
					.lastname("Clark")
					.email("laura@example.com")
					.password("password")
					.role(USER)
					.build();

			var user2 = RegisterRequest.builder()
					.firstname("John")
					.lastname("Doe")
					.email("john@example.com")
					.password("password")
					.role(USER)
					.build();

			var user3 = RegisterRequest.builder()
					.firstname("Jane")
					.lastname("Smith")
					.email("jane@example.com")
					.password("password")
					.role(USER)
					.build();

			var user4 = RegisterRequest.builder()
					.firstname("Mike")
					.lastname("Johnson")
					.email("mike@example.com")
					.password("password")
					.role(USER)
					.build();

			var user5 = RegisterRequest.builder()
					.firstname("Sarah")
					.lastname("Williams")
					.email("sarah@example.com")
					.password("password")
					.role(USER)
					.build();

			var user6 = RegisterRequest.builder()
					.firstname("David")
					.lastname("Davis")
					.email("david@example.com")
					.password("password")
					.role(USER)
					.build();

			var user7 = RegisterRequest.builder()
					.firstname("Emily")
					.lastname("Anderson")
					.email("emily@example.com")
					.password("password")
					.role(USER)
					.build();

			var user8 = RegisterRequest.builder()
					.firstname("Daniel")
					.lastname("Wilson")
					.email("daniel@example.com")
					.password("password")
					.role(USER)
					.build();

			var user9 = RegisterRequest.builder()
					.firstname("Jessica")
					.lastname("Thomas")
					.email("jessica@example.com")
					.password("password")
					.role(USER)
					.build();

			var user10 = RegisterRequest.builder()
					.firstname("Michael")
					.lastname("Lee")
					.email("michael@example.com")
					.password("password")
					.role(USER)
					.build();

			System.out.println("Admin token: " + service.register(admin).getAccessToken());
			System.out.println("Manager token: " + service.register(manager).getAccessToken());
			System.out.println("User token: " + service.register(user1).getAccessToken());
			System.out.println("User2 token: " + service.register(user2).getAccessToken());
			System.out.println("User3 token: " + service.register(user3).getAccessToken());
			System.out.println("User4 token: " + service.register(user4).getAccessToken());
			System.out.println("User5 token: " + service.register(user5).getAccessToken());
			System.out.println("User6 token: " + service.register(user6).getAccessToken());
			System.out.println("User7 token: " + service.register(user7).getAccessToken());
			System.out.println("User8 token: " + service.register(user8).getAccessToken());
			System.out.println("User9 token: " + service.register(user9).getAccessToken());
			System.out.println("User10 token: " + service.register(user10).getAccessToken());
		};
	}
}