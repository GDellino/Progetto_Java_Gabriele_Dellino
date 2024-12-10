package it.aulab.progetto_finale_gabriele_dellino;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProgettoFinaleGabrieleDellinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoFinaleGabrieleDellinoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper istanceModelMapper(){
		ModelMapper mapper = new ModelMapper();
		return mapper;
	}

}
