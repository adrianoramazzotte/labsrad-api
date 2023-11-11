package com.ramazzotte.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.Tenant;
import com.ramazzotte.repository.query.PacienteRepositoryQuery;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>, PacienteRepositoryQuery {
	

	@Query(value = "select * from paciente where status=1 and tenant_id = ?", nativeQuery = true)
	List<Paciente> findAllSql(int idtenant);
	@Query(value = "select * from paciente where status=0", nativeQuery = true)
	List<Paciente> findAllSqlInativo();
	@Query(value = "select * from paciente where nome = ? and datanasc = ?", nativeQuery = true)
	Paciente findByNomeAndDatanasc(String nome, LocalDate dataNasc);
	
	Paciente findByCpf(String cpf);
	Paciente findByNomeAndDatanascAndTenant(String nome, Date datanasc, Tenant buscarOuFalhar);

	Paciente findByCpfAndTenant(String cpf, Tenant buscarOuFalhar);


	
}
