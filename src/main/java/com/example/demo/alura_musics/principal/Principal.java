package com.example.demo.alura_musics.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.demo.alura_musics.model.Artista;
import com.example.demo.alura_musics.model.Tipo;
import com.example.demo.alura_musics.repository.ArtistaRepository;

public class Principal {
	
	private ArtistaRepository artistaRepository;
	
	public Principal(ArtistaRepository artistaRepository) {
		this.artistaRepository = artistaRepository;
	}

	Scanner sc = new Scanner(System.in);

	public void exibirMenu() {
		Integer opcao = -1;

		while (opcao != 0) {
			System.out.println();
			System.out.println("Selecione uma opção: \n" + "1- Cadastrar artistas \n" + "2- Cadastrar músicas \n"
					+ "3- Listar músicas \n" + "4- Buscar músicas por artistas \n"
					+ "5- Pesquisar dados sobre um artista \n\n" + "0- Sair \n");

			opcao = sc.nextInt();
			sc.nextLine();

			System.out.println();
			switch (opcao) {
			case 1:
				System.out.println("Para ser implementado!");
				break;
			case 2:
				System.out.println("Para ser implementado!");
				break;
			case 3:
				System.out.println("Para ser implementado!");
				break;
			case 4:
				System.out.println("Para ser implementado!");
				break;
			case 5:
				System.out.println("Para ser implementado!");
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}

		}
		
	}
	
	public void registrar() {
		List<Artista> artistas = Arrays.asList(
		new Artista(null, "Raul Seixas", Tipo.SOLO),
		new Artista(null, "Legião Urbana", Tipo.BANDA),
		new Artista(null, "Twenty One Pilots", Tipo.DUPLA)
		);
		
		artistaRepository.saveAll(artistas);
	}
}





