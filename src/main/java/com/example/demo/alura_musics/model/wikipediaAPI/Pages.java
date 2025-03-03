package com.example.demo.alura_musics.model.wikipediaAPI;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Pages(
		@JsonAlias("page") Page page) {

}
