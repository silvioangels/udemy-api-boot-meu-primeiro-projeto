package com.udemy.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.app.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	Empresa findByCnpj(String cnpj);

}
