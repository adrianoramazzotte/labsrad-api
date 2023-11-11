
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.Tenant;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {

	Convenio findByDescricao(String descricao);

	    @Query(value= "select * from convenio where status=1 and tenant_id = ? ", nativeQuery = true)
	List<Convenio> findAllSql(int id);
    @Query(value= "select * from convenio where status=0 and tenant_id = ?", nativeQuery = true)
  	List<Convenio> findAllSqlInativo(int id);
    
    @Query(value= "select * from convenio where id= ?", nativeQuery = true)
	Convenio findByCodigo( int idconvenio);
    @Query(value= "select * from convenio where tenant_id = ?", nativeQuery = true)
	List<Convenio> convenio(Integer tenant);
    @Query(value= "select c.* from convenio c, exame_convenio e\r\n" + 
    		"where c.id = e.convenio_id and e.exame_id = ?", nativeQuery = true)
	List<Convenio> findAllSqlConv(int id);

	Convenio findByDescricaoAndTenant(String descricao, Tenant buscarOuFalhar);
	
			
	@Query(value= "SELECT * from convenio   where id in (\r\n"
			+ "			select convenio_id from exame_convenio where exame_id = ?) and tenant_id  = ?", nativeQuery = true)
	List<Convenio> findAllSqlExameId(Integer idexame, Integer buscarOuFalharInt );

	
}
