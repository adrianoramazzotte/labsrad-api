package com.ramazzotte.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.repository.filter.AtendimentoFilter;

public interface AtendimentoRepositoryQuery {
		
		public Page<Atendimento> filtrar(AtendimentoFilter atendimentoFilter, Pageable pageable);

	

}
