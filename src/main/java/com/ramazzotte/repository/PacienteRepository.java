
package com.ramazzotte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Paciente;



@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	Paciente findByCpf(String cpf);

    


}
