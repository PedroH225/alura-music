package com.example.demo.alura_musics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.alura_musics.principal.Principal;
import com.example.demo.alura_musics.repository.ArtistaRepository;

@SpringBootApplication
public class AluraMusicsApplication implements CommandLineRunner {
	
	private ArtistaRepository artistaRepository;
	
	public AluraMusicsApplication(ArtistaRepository artistaRepository) {
		this.artistaRepository = artistaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AluraMusicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository);
		
		principal.registrar();
		principal.exibirMenu();
		
	}

}
