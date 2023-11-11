package com.ramazzotte.repository.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.domain.Tenant;
import com.ramazzotte.repository.filter.AtendimentoFilter;
import com.ramazzotte.repository.query.AtendimentoRepositoryQuery;
import com.ramazzotte.service.util.Tenantuser;



public class AtendimentoRepositoryImpl implements AtendimentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private Tenantuser tenantUsuario;

	@Override
	public Page<Atendimento> filtrar(AtendimentoFilter atendimentofilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		// select * from cliente
		// from Cliente
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
//		where
		Root<Atendimento> root = criteria.from(Atendimento.class);
		From<?, ?> logJoin = root.join("logs", JoinType.LEFT);
		From<?, ?> pacienteJoin = root.join("paciente", JoinType.INNER);
		criteria.distinct(true);
		List<Order> orderList = new ArrayList();
		orderList.add(builder.desc(root.get("id")));
		criteria.orderBy(orderList);
		

		Predicate[] predicates = criarRestricoes(atendimentofilter, builder, root, logJoin,pacienteJoin );
		criteria.where(predicates);

		TypedQuery<Atendimento> query = manager.createQuery(criteria);
		List<Atendimento> listacarga = new ArrayList<>();
		listacarga = query.getResultList();
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, listacarga.size());

	}

	private Predicate[] criarRestricoes(AtendimentoFilter atendimentofilter, CriteriaBuilder builder, Root<Atendimento> root,
			From<?, ?> logJoin,From<?, ?> pacienteJoin ) {
		List<Predicate> predicates = new ArrayList<>();
		Tenant t = tenantUsuario.buscarOuFalhar();
		predicates.add(builder.equal(builder.lower(root.get("tenant")), t));
		if (atendimentofilter.getId() != null) {
			
			try {
				Integer valor = Integer.parseInt(atendimentofilter.getId());
				predicates.add(builder.equal(builder.lower(root.get("id")), valor));
			} catch (Exception e) {
				predicates.add(builder.equal(builder.lower(root.get("id")), 0));
			}
			
		}

		if (atendimentofilter.getIdficha() != null) {			
			try {
				Integer valor = Integer.parseInt(atendimentofilter.getIdficha());
				predicates.add(builder.equal(builder.lower(root.get("idFicha")), valor));	
			} catch (Exception e) {
				predicates.add(builder.equal(builder.lower(root.get("idFicha")), 0));
			}			
		}
		if (atendimentofilter.getNome() != null) {
			predicates.add(
					builder.like(builder.lower(pacienteJoin.get("nome")), "%" + atendimentofilter.getNome() + "%"));
		}
		if (atendimentofilter.getFormapagamento() != null) {
			predicates.add(
					builder.like(builder.lower(root.get("formapagamento")), "%" + atendimentofilter.getFormapagamento() + "%"));
		}
	
		if (atendimentofilter.getDatanascde() != null) {
//			Instant instant = atendimentofilter.getDatanascde().toInstant();
//			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
//			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.greaterThanOrEqualTo(pacienteJoin.get("datanasc"), atendimentofilter.getDatanascde()));
		}
		if (atendimentofilter.getDatanascate() != null) {
//			Instant instant = atendimentofilter.getDatanascate().toInstant();
//			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
//			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.lessThanOrEqualTo(pacienteJoin.get("datanasc"), atendimentofilter.getDatanascate()));
		}
		
		if (atendimentofilter.getDatalancamentode() != null) {
//			Instant instant = atendimentofilter.getDatalancamentode().toInstant();
//			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
//			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.greaterThanOrEqualTo(root.get("datalancamento"), atendimentofilter.getDatalancamentode()));
		}
		if (atendimentofilter.getDatalancamentoate() != null) {
//			Instant instant = atendimentofilter.getDatalancamentoate().toInstant();
//			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
//			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			
			predicates.add(builder.lessThanOrEqualTo(root.get("datalancamento"), atendimentofilter.getDatalancamentoate()));
		}
		
		if (atendimentofilter.getSexo() != null) {
			predicates.add(
					builder.like(builder.lower(pacienteJoin.get("sexo")), "%" + atendimentofilter.getSexo() + "%"));
		}

		if (atendimentofilter.getEmailusuario() != null) {
			predicates.add(builder.like(builder.lower(logJoin.get("emailUsuario")),
					"%" + atendimentofilter.getEmailusuario() + "%"));
		}
		
		if (atendimentofilter.getStatus().equals("Ativos")) {
			predicates.add(builder.equal(builder.lower(root.get("status")), true));
		} else {
			predicates.add(builder.equal(builder.lower(root.get("status")), false));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Atendimento> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();

		int totalRegistrosPorPagina = pageable.getPageSize();

		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Long total(AtendimentoFilter atendimentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
