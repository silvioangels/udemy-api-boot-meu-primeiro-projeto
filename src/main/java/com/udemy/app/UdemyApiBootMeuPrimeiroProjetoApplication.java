package com.udemy.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.udemy.app.utils.SenhaUtils;

@SpringBootApplication
public class UdemyApiBootMeuPrimeiroProjetoApplication {
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	public static void main(String[] args) {
		SpringApplication.run(UdemyApiBootMeuPrimeiroProjetoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("### Quantidade de elementos por pagina = " + this.qtdPorPagina);
			
			String senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded: " + senhaEncoded);	
			
			senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded novamente: " + senhaEncoded);	

			System.out.println("Senha válida: " + SenhaUtils.senhaValida("123456", senhaEncoded));	
		};
	}
	
}
