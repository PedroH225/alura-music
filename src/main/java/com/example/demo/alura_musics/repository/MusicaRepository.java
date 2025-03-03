package com.example.demo.alura_musics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.alura_musics.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, String> {

	List<Musica> findAllByArtistaNomeContainsIgnoreCase(String artistaNome);
}
