package com.example.demo.alura_musics.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
