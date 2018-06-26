package br.com.fastshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FastshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastshopApplication.class, args);
		System.out.println("Password: " + new BCryptPasswordEncoder().encode("123"));
	}
}
