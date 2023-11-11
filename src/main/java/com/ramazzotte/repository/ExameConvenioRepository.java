
package com.ramazzotte.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;

@Repository
public interface ExameConvenioRepository extends JpaRepository<ExameConvenio, Integer> {

	
	@Modifying
	@Transactional	
	@Query(value= "delete from exame_convenio where exame_id = ?", nativeQuery = true)
	void deleteByIdExame(Integer id);

    @Query(value= "SELECT * FROM exame_convenio where exame_id = ? and convenio_id = ?", nativeQuery = true)
    ExameConvenio buscarExameConvenio(int idExame, int idConvenio);
    
    
    @Query(value= "SELECT e.* FROM exame e, exame_convenio c  where c.convenio_id = ? and e.id = c.exame_id", nativeQuery = true)
	List<Exame> buscarExamePorConvenio(Integer idConvenio);
    
	 @Query(value= "SELECT * FROM exame_convenio  where tenant_id = ?", nativeQuery = true)
	List<ExameConvenio> exame(Integer tenant);


	
}
