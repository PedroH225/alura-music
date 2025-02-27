package com.example.demo.alura_musics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.alura_musics.principal.Principal;

@SpringBootApplication
public class AluraMusicsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AluraMusicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		
		principal.exibirMenu();
		
	}

}
