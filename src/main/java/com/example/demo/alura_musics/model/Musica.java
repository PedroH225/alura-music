package com.example.demo.alura_musics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {

	@Id 
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String nome;
	
	private String album;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "artista_id")
	private Artista artista;
	
	public Musica() {
		// TODO Auto-generated constructor stub
	}
	
	public Musica(String id, String nome, String album, Artista artista) {
		this.id = id;
		this.nome = nome;
		this.album = album;
		this.artista = artista;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	@Override
	public String toString() {
		return "Musica: " + nome + ", álbum: " + album + ", artista: " + artista.getNome();
	}
	
	

}
