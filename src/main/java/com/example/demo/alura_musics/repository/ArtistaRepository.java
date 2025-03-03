package com.example.demo.alura_musics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.alura_musics.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, String>{

}
