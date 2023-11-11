package com.ramazzotte.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.AtendimentoItens;


@Repository
public interface AtendimentoItensRepository extends JpaRepository<AtendimentoItens, Integer> {

		@Modifying
	@Transactional	
	@Query(value= "delete from atendimento_itens where atendimento_id = ?", nativeQuery = true)
	void deleteByIdAtendimento(Integer id);
		
		@Query(value= "Select sum(preco) Valor, extract(month from data_atendimento) mes, extract(year from data_atendimento) ano "
				+ "from atendimento_itens group by extract(month from data_atendimento),extract(year from data_atendimento)", nativeQuery = true)
		List<Object> buscarValorGraficoExameusuario();
		@Query(value= "Select max(acesso +1) from atendimento_itens", nativeQuery = true)
		String buscarAcessoMaisUm();
		@Query(value= "Select * from atendimento_itens where atendimento_id = ? and tenant_id = ?", nativeQuery = true)
		List<AtendimentoItens> findPorAtendimento(String atendimento, Integer tenant);
		
		@Query(value= "Select * from atendimento_itens where atendimento_id = ?", nativeQuery = true)
		List<AtendimentoItens> findPorAtendimentoID(Integer atendimento);





	
}
