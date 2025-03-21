package com.example.demo.alura_musics.principal;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;

import com.example.demo.alura_musics.model.Artista;
import com.example.demo.alura_musics.model.Musica;
import com.example.demo.alura_musics.model.Tipo;
import com.example.demo.alura_musics.model.wikipediaAPI.Page;
import com.example.demo.alura_musics.model.wikipediaAPI.Pages;
import com.example.demo.alura_musics.model.wikipediaAPI.WikipediaQueryDTO;
import com.example.demo.alura_musics.repository.ArtistaRepository;
import com.example.demo.alura_musics.repository.MusicaRepository;
import com.example.demo.alura_musics.service.ConsumoApi;
import com.example.demo.alura_musics.service.ConverteDados;

public class Principal {

	private ArtistaRepository artistaRepository;

	private MusicaRepository musicaRepository;

	Scanner sc = new Scanner(System.in);

	ConsumoApi consumoApi = new ConsumoApi();
	ConverteDados conversor = new ConverteDados();
	private final String ENDERECO = "https://pt.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=1&explaintext=1&titles=";

	public Principal(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
	}

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
				cadastrarArtistas();
				break;
			case 2:
				cadastrarMusicas();
				break;
			case 3:
				listarMusicas();
				break;
			case 4:
				buscarPorArtista();
				break;
			case 5:
				pesquisarArtista();
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}

		}

	}

	private void buscarPorArtista() {
		List<Artista> artistas = artistaRepository.findAll();

		String opcao = "s";
		while (!opcao.equalsIgnoreCase("n")) {
			artistas.forEach(a -> {
				System.out.println("Artista: " + a.getNome());
			});
			System.out.println("Digite o nome do artista desejado: ");
			String artistaNome = sc.nextLine();

			List<Musica> buscarMusicas = musicaRepository.findAllByArtistaNomeContainsIgnoreCase(artistaNome);

			System.out.println();
			if (!buscarMusicas.isEmpty()) {
				System.out.println("Músicas de " + buscarMusicas.getFirst().getArtista().getNome() + " encontradas:");
				buscarMusicas.forEach(m -> {
					System.out.println("Música: " + m.getNome() + ", álbum: " + m.getAlbum() + ".");
				});
			} else {
				System.out.println("Nenhum artista encontrado.");
			}

			System.out.println();
			System.out.println("Deseja fazer outra busca? (s/n)");
			opcao = sc.nextLine();

			while (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
				System.out.println();
				System.out.println("Opção inválida!");
				System.out.println("Deseja fazer outra busca? (s/n)");
				opcao = sc.nextLine();
			}

			System.out.println();
		}

	}

	private void cadastrarMusicas() {
		List<Artista> artistas = artistaRepository.findAll();

		String opcao = "s";
		while (!opcao.equals("n")) {
			System.out.println("Digite o nome da música:");
			String nome = sc.nextLine();

			System.out.println();
			System.out.println("Digite o álbum da música:");
			String album = sc.nextLine();

			System.out.println();
			artistas.forEach(a -> {
				System.out.println("Artista: " + a.getNome());
			});
			System.out.println("Digite o nome do artista desejado: ");
			String artistaNome = sc.nextLine();

			Optional<Artista> buscarArtista = artistaRepository.findByNomeContainsIgnoreCase(artistaNome);

			if (buscarArtista.isPresent()) {
				musicaRepository.save(new Musica(null, nome, album, buscarArtista.get()));

				System.out.println();
				System.out.println("Música cadastrada com sucesso!");
			} else {
				System.out.println("Nenhum artista encontrado!");
			}
			System.out.println();

			System.out.println("Deseja adicionar outra música? (s/n)");
			opcao = sc.nextLine();

			while (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
				System.out.println();
				System.out.println("Opção inválida!");
				System.out.println("Deseja adicionar outra música? (s/n)");
				opcao = sc.nextLine();
			}

			System.out.println();
		}

	}

	private void listarMusicas() {
		List<Musica> musicas = musicaRepository.findAllByOrderByArtistaNome();

		if (!musicas.isEmpty()) {
			System.out.println("Músicas registradas:");
			musicas.forEach(System.out::println);

		} else {
			System.out.println("Nenhuma música registrada.");
		}

	}

	public void registrar() {
		List<Artista> artistas = Arrays.asList(new Artista(null, "Raul Seixas", Tipo.SOLO),
				new Artista(null, "Legião Urbana", Tipo.BANDA), new Artista(null, "Twenty One Pilots", Tipo.DUPLA));

		List<Artista> dbArtistas = artistaRepository.saveAll(artistas);

		List<Musica> musicas = Arrays.asList(
				// Raul Seixas
				new Musica(null, "O dia em que a terra parou", "O Dia em Que a Terra Parou", dbArtistas.get(0)),
				new Musica(null, "Eu nasci há 10 mil anos atrás", "Metrô Linha 743", dbArtistas.get(0)),
				new Musica(null, "Capim Guiné", "A Pedra do Gênesis", dbArtistas.get(0)),

				// Legião Urbana
				new Musica(null, "Monte Castelo", "As Quatro Estações", dbArtistas.get(1)),
				new Musica(null, "Tempo Perdido", "Dois", dbArtistas.get(1)),
				new Musica(null, "Geração Coca-Cola", "Legião Urbana", dbArtistas.get(1)),

				// Twenty One Pilots
				new Musica(null, "Ride", "Blurryface", dbArtistas.get(2)),
				new Musica(null, "The Judge", "Blurryface", dbArtistas.get(2)),
				new Musica(null, "Shy Away", "Scaled and Icy", dbArtistas.get(2))

		);

		musicaRepository.saveAll(musicas);

	}

	private void cadastrarArtistas() {
		var opcao = "s";
		while (!opcao.equalsIgnoreCase("n")) {
			System.out.println("Digite o nome do artista:");
			var nome = sc.nextLine();

			System.out.println();
			System.out.println("Digite o tipo do artista(solo/dupla/banda):");
			var tipo = sc.nextLine();

			System.out.println();

			try {
				Artista novoArtista = new Artista(null, nome, Tipo.fromString(tipo));
				System.out.println("Artista adicionado com sucesso!");
				System.out.println();

				artistaRepository.save(novoArtista);

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println();
			}

			System.out.println("Deseja adicionar outro artista? (s/n)");
			opcao = sc.nextLine();

			while (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
				System.out.println("Opção inválida!");
				System.out.println();

				System.out.println("Deseja adicionar outro artista? (s/n)");
				opcao = sc.nextLine();
			}
		}

	}

	private void pesquisarArtista() {
		String opcao = "s";
		while (!opcao.equals("n")) {
			List<Artista> artistas = artistaRepository.findAll();
			artistas.forEach(a -> {
				System.out.println("Artista: " + a.getNome());
			});
			System.out.println("Digite o nome do artista desejado: ");
			String artistaNome = sc.nextLine();

			Optional<Artista> buscarArtista = artistaRepository.findByNomeContainsIgnoreCase(artistaNome);

			System.out.println();
			if (buscarArtista.isPresent()) {

				var json = consumoApi.obterDados(ENDERECO + buscarArtista.get().getNome().replace(" ", "+"));
				WikipediaQueryDTO buscaWikipedia = conversor.obterDados(json, WikipediaQueryDTO.class);

				Optional<Page> pagina = buscaWikipedia.query().pages().values().stream().findFirst();

				System.out.println();
				if (pagina.isPresent() && pagina.get().extract() != null) {
					String resumo = pagina.get().extract();

					System.out.println(resumo);
				} else {

					System.out.println("Resumo não encontrado.");
				}
			} else {
				System.out.println("Artista não encontrado!");
			}
			
			System.out.println();
			System.out.println("Deseja fazer outra busca? (s/n)");
			opcao = sc.nextLine();

			while (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
				System.out.println();
				System.out.println("Opção inválida!");
				System.out.println("Deseja fazer outra busca? (s/n)");
				opcao = sc.nextLine();
			}

			System.out.println();
		}
	}
}
