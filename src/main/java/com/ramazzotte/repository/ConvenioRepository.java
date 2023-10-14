
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Convenio;



@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {

    @Query(value= "select c.* from convenio c, exame_convenio e\r\n" + 
    		"where c.id = e.convenio_id and e.exame_id = ?", nativeQuery = true)
	List<Convenio> findAllSqlConv(int id);
    @Query(value= "select * from convenio", nativeQuery = true)
	List<Convenio> buscarTodos();
    
    @Query(value= "select * from convenio where id = ?", nativeQuery = true)
	Convenio buscarPorId(Integer id);

	Convenio findByDescricao(String descricao);


}
