
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.Tenant;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {
	@Query(value= "select * from exame where id = ?", nativeQuery = true)
	Exame findPorId(Integer id);
	
	
	Exame findByDescricao(String descricao);

	@Query(value= "select * from exame where status=1 and tenant_id = ?", nativeQuery = true)
	List<Exame> findAllSql(int idtenant);
    @Query(value= "select * from exame where status=0", nativeQuery = true)
  	List<Exame> findAllSqlInativo();
    @Query(value= "SELECT e.* FROM exame e, exame_convenio c  where c.convenio_id = ? and e.id = c.exame_id and e.status = 1", nativeQuery = true)
  	List<Exame> findAllPorConvenio(int idConvenio);

	@Query(value= "select * from exame where id= ?", nativeQuery = true)
	Exame findByCodigo( int idexame);
	



	Exame findByDescricaoAndTenant(String descricao, Tenant buscarOuFalhar);

	

	

	
}
