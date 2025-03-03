package com.example.demo.alura_musics.model;

public enum Tipo {
	SOLO("solo"),
	DUPLA("dupla"),
	BANDA("banda");
	
	public String tipo;
	
	private Tipo(String tipo) {
		this.tipo = tipo;
	}

	public static Tipo fromString(String text) {
        for (Tipo categoria : Tipo.values()) {
            if (categoria.tipo.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
