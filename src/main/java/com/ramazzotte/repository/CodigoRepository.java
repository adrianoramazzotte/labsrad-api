package com.ramazzotte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Codigo;


@Repository
public interface CodigoRepository extends JpaRepository<Codigo, Integer> {

	@Query(value= "SELECT * FROM codigo where id = ?", nativeQuery = true)
	Codigo findPorId(Integer id);
	
	@Query(value= "SELECT * FROM codigo where telefone = ?", nativeQuery = true)
	Codigo findPorTelefone(String id);



	Codigo findByTelefone(String telefona);
	
	
}
