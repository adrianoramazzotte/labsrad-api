
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;



@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {

	@Query(value= "SELECT * FROM exame", nativeQuery = true)
	List<Exame> findAll();
	@Query(value= "SELECT * FROM exame where id = ?", nativeQuery = true)
	Exame findPorID(Integer id);


}
