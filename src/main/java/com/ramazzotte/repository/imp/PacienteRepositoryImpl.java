package com.ramazzotte.repository.imp;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.repository.filter.PacienteFilter;
import com.ramazzotte.repository.query.PacienteRepositoryQuery;

public class PacienteRepositoryImpl implements PacienteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Paciente> filtrar(PacienteFilter pacientefilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		criteria.distinct(true);
		List<Order> orderList = new ArrayList<>();
		orderList.add(builder.desc(root.get("id")));
		criteria.orderBy(orderList);		
		Predicate[] predicates = criarRestricoes(pacientefilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Paciente> query = manager.createQuery(criteria);
		List<Paciente> listacarga = new ArrayList<>();
		listacarga = query.getResultList();
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, listacarga.size());

	}

	private Predicate[] criarRestricoes(PacienteFilter pacientefilter, CriteriaBuilder builder, Root<Paciente> root) {
		List<Predicate> predicates = new ArrayList<>();
//		private String id;	
//		private String nome;

		if (pacientefilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + pacientefilter.getNome() + "%"));
		}
		

		
		if (pacientefilter.getDatanascde() != null) {
			Instant instant = pacientefilter.getDatanascde().toInstant();
			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.greaterThanOrEqualTo(root.get("datausucriacao"), offsetDateTime));
		}
		if (pacientefilter.getDatanascate() != null) {
			Instant instant = pacientefilter.getDatanascate().toInstant();
			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.lessThanOrEqualTo(root.get("datausucriacao"), offsetDateTime.plusDays(1)));
		}
		

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Paciente> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();

		int totalRegistrosPorPagina = pageable.getPageSize();

		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

}
