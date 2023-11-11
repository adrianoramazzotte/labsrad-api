
package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Classepermissao;

@Repository
public interface ClassepermissaoRepository extends JpaRepository<Classepermissao, Integer> {

	
	    @Query(value= "select * from classepermissao ", nativeQuery = true)
	List<Classepermissao> findAllSql();
    @Query(value= "select * from classepermissao", nativeQuery = true)
  	List<Classepermissao> findAllSqlInativo();
    
    @Query(value= "SELECT count(i.codigo) Exames, e.descricao FROM atendimento_itens i, exame e\r\n" + 
    		"where i.status = 1 and i.tenant_id = ? and MONTH(i.data_atendimento) = MONTH(CURRENT_DATE()) and e.id = i.exame_id\r\n" + 
    		" group by i.exame_id", nativeQuery = true)
  	List<String> buscarExames(Integer tenant_id);
    
    @Query(value= "SELECT count(i.exame_id),u.nome  FROM atendimento a, atendimento_itens i, usuario u\r\n" + 
    		"where i.status = 1 and    i.tenant_id = ? and MONTH(i.data_atendimento) = MONTH(CURRENT_DATE()) and a.id = i.atendimento_id and a.usuario_id = u.id and u.id !=3 group by a.usuario_id;", nativeQuery = true)
  	List<String> buscarExameProUsuario(Integer tenant_id);
    
    @Query(value= "Select count(codigo) Exames,  extract(month from data_atendimento) mes,   extract(year from data_atendimento) ano \r\n" + 
    		"from atendimento_itens  where status = 1 and  tenant_id = ?  group by   extract(month from data_atendimento),   extract(year from data_atendimento) order by ano, mes", nativeQuery = true)
  	List<String> buscarExamePorMesAno(Integer tenant_id);

	
}
