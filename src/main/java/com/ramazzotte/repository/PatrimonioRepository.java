
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {

	Patrimonio findByDescricao(String descricao);

	    @Query(value= "select * from patrimonio where status=1 and tenant_id = ? ", nativeQuery = true)
	List<Patrimonio> findAllSql(int id);
    @Query(value= "select * from patrimonio where status=0 and tenant_id = ?", nativeQuery = true)
  	List<Patrimonio> findAllSqlInativo(Integer integer);
    
    @Query(value= "select * from patrimonio where id= ?", nativeQuery = true)
    Patrimonio findByCodigo( int idconvenio);
    @Query(value= "select * from patrimonio where tenant_id = ?", nativeQuery = true)
	List<Patrimonio> convenio(Integer tenant);
    @Query(value= "select c.* from patrimonio c, exame_convenio e\r\n" + 
    		"where c.id = e.patrimonio_id and e.exame_id = ?", nativeQuery = true)
	List<Patrimonio> findAllSqlConv(int id);

	

	
}
