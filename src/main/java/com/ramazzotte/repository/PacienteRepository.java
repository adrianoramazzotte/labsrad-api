
package com.ramazzotte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.repository.query.PacienteRepositoryQuery;



@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>,
   PacienteRepositoryQuery {

	Paciente findByCpf(String cpf);

    


}
