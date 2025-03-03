package com.example.demo.alura_musics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.alura_musics.model.Musica;
import com.example.demo.alura_musics.principal.Principal;
import com.example.demo.alura_musics.repository.ArtistaRepository;
import com.example.demo.alura_musics.repository.MusicaRepository;

@SpringBootApplication
public class AluraMusicsApplication implements CommandLineRunner {
	
	private ArtistaRepository artistaRepository;
	
	private MusicaRepository musicaRepository;
	
	public AluraMusicsApplication(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AluraMusicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository, musicaRepository);
		
		principal.registrar();
		principal.exibirMenu();
		
	}

}
