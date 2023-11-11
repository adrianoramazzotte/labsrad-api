package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.repository.query.AtendimentoRepositoryQuery;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>, AtendimentoRepositoryQuery {
	@Query(value= "select * from atendimento where  id = ?", nativeQuery = true)
	Atendimento findPorId(Integer id);
	
    @Query(value= "select * from atendimento where status=1 and tenant_id = ? order by id desc ", nativeQuery = true)
	List<Atendimento> findAllSql(int tenant);
    @Query(value= "select * from atendimento where status=0 and tenant_id = ?", nativeQuery = true)
  	List<Atendimento> findAllSqlInativo(int tenant);
    @Query(value= "select a.forma_pagamento,  a.id_ficha as Prontuario, ai.atendimento_id as Atendimento, ai.data_atendimento \n" + 
    		"as DataLancamento, ai.preco, ai.desconto, ai.total, p.nome, u.nome as Usuario, e.descricao as Exame , c.descricao as Convenio,\n" + 
    		"p.nome as Paciente\n" + 
    		"from atendimento a, atendimento_itens ai, paciente p, usuario u, exame e, convenio c\n" + 
    		"where a.id = ai.atendimento_id \n" + 
    		"and a.paciente_id = p.id \n" + 
    		"and u.id = a.usuario_id \n" + 
    		"and e.id = ai.exame_id\n" + 
    		"and c.id = ai.convenio_id\n" + 
    		"and ai.data_atendimento between ? and ?", nativeQuery = true)
	List<Atendimento> findAllSqlRelDataIniFin(String ini, String fim);
    @Query(value= "SELECT distinct(a.id), a.tenant_id, a.id_ficha, a.status, a.formapagamento, a.paciente_id, a.desconto, a.valortotal, a.datalancamento as datalancamento, a.usuario_id FROM atendimento_itens ai, atendimento a\n" + 
    		"where a.id = ai.atendimento_id and ai.data_atendimento between ? and ? and ai.tenant_id = ?  order by usuario_id, id_ficha", nativeQuery = true)    
	List<Atendimento> porDatas(String ini, String fim, Integer tenant);
    @Query(value= "SELECT distinct(a.id), a.id_ficha, a.status, a.formapagamento, a.tenant_id, a.paciente_id, a.desconto, a.valortotal, a.datalancamento as datalancamento, a.usuario_id FROM atendimento_itens ai, atendimento a \n" + 
    		"where a.id = ai.atendimento_id and extract(month from data_atendimento)= ?\n" + 
    		"and extract(year from data_atendimento)= ? and a.usuario_id = ? and ai.tenant_id = ? order by usuario_id, id_ficha", nativeQuery = true)
	List<Atendimento> mensalUsuario(Integer mes, Integer ano, Integer usuario, Integer tenant);
    
    @Query(value= "SELECT distinct(a.id), a.id_ficha, a.formapagamento, a.tenant_id, a.valortotal, a.status, a.paciente_id, a.desconto, a.datalancamento as datalancamento, a.usuario_id FROM atendimento_itens ai, atendimento a \n" + 
    		"where  a.status = 1 and a.id = ai.atendimento_id and extract(month from ai.data_atendimento)= ?\n" + 
    		"and extract(year from ai.data_atendimento)= ? and ai.tenant_id = ? order by usuario_id, id_ficha", nativeQuery = true)
	List<Atendimento> mensal(Integer mes, Integer ano, Integer tenant);
    
    @Query(value= "SELECT distinct(a.id), a.id_ficha, a.status, a.formapagamento, a.tenant_id, a.paciente_id, a.desconto, a.valortotal, a.datalancamento as datalancamento, a.usuario_id FROM atendimento_itens ai, atendimento a \r\n"
    		+ "where a.id = ai.atendimento_id and extract(month from data_atendimento)= ?\r\n"
    		+ "and extract(year from data_atendimento)= ? and ai.convenio_id = ? and ai.tenant_id = ? order by usuario_id, id_ficha", nativeQuery = true)
	List<Atendimento> mensalConvenio(Integer mes, Integer ano, Integer usuario, Integer tenant);
}
