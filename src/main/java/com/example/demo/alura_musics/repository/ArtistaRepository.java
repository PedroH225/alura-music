package com.example.demo.alura_musics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.alura_musics.model.Artista;
import java.util.List;


public interface ArtistaRepository extends JpaRepository<Artista, String>{

	Optional<Artista> findByNomeContainsIgnoreCase(String nome);;
}
