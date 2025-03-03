package com.example.demo.alura_musics.model.wikipediaAPI;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Query(
		@JsonProperty("pages") Map<String, Page> pages
) {

}
