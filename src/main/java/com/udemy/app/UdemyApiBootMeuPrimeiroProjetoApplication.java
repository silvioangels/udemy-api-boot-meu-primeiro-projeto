package com.udemy.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.udemy.app.entities.Empresa;
import com.udemy.app.repositories.EmpresaRepository;
import com.udemy.app.utils.SenhaUtils;

@SpringBootApplication
public class UdemyApiBootMeuPrimeiroProjetoApplication {
	
	@Autowired
	private EmpresaRepository empresaRepository;	
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	public static void main(String[] args) {
		SpringApplication.run(UdemyApiBootMeuPrimeiroProjetoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("###############################################################");
			System.out.println("### EXEMPLO LEITURA PROPERTIES");
			System.out.println("### Quantidade de elementos por pagina = " + this.qtdPorPagina);
			System.out.println("###############################################################");
			
			System.out.println("###############################################################");
			System.out.println("### EXEMPLO BCRYPT");
			String senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded: " + senhaEncoded);	
			
			senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded novamente: " + senhaEncoded);	

			System.out.println("Senha válida: " + SenhaUtils.senhaValida("123456", senhaEncoded));	
			System.out.println("###############################################################");
			
			System.out.println("###############################################################");
			System.out.println("### EXEMPLO JPAREPOSITORY");
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("Kazale IT");
			empresa.setCnpj("74645215000104");
			
			this.empresaRepository.save(empresa);

			List<Empresa> empresas = empresaRepository.findAll();
			empresas.forEach(System.out::println);
			
			Optional<Empresa> empresaDb = empresaRepository.findById(1L);
			System.out.println("---->Empresa por ID: " + empresaDb);
			
			empresaDb.get().setRazaoSocial("Kazale IT Web");
			this.empresaRepository.save(empresaDb.get());

			Empresa empresaCnpj = empresaRepository.findByCnpj("74645215000104");
			System.out.println("---->Empresa por CNPJ: " + empresaCnpj);
			
			this.empresaRepository.deleteById(1L);
			empresas = empresaRepository.findAll();
			System.out.println("---->Empresas: " + empresas.size());
			System.out.println("###############################################################");
		};
	}
	
}
