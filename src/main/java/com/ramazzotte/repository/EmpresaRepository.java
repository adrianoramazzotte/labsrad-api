
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Empresa;



@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query(value= "select * from empresa", nativeQuery = true)
	List<Empresa> buscarTodos();
    
    @Query(value= "select * from empresa where id = ?", nativeQuery = true)
	Empresa buscarPorId(Integer id);


}
