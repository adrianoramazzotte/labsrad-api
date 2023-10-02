
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.ExameConvenio;



@Repository
public interface ExameConvenioRepository extends JpaRepository<ExameConvenio, Integer> {

	@Query(value= "SELECT * FROM exame_convenio", nativeQuery = true)
	List<ExameConvenio> findAll();


}
