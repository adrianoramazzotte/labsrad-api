package com.ramazzotte.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Tenant;



@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
	
	@Query(value= "select * from tenant where id = ?", nativeQuery = true) 
	Tenant findPorId(int codigoTenant);
	
	@Query(value= "select razao from empresa where tenant_id = ?", nativeQuery = true) 
	String findEmpresaTenant(int codigoTenant);
    @Modifying
    @Transactional
	@Query(value= "select id from tenant where descricao = ?", nativeQuery = true) 
	Integer buscarIdTenant(String descricao);
    

	
}
