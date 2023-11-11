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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.Tenant;
import com.ramazzotte.repository.filter.PacienteFilter;
import com.ramazzotte.repository.query.PacienteRepositoryQuery;
import com.ramazzotte.service.util.Tenantuser;



public class PacienteRepositoryImpl implements PacienteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private Tenantuser tenantUsuario;
	@Override
	public Page<Paciente> filtrar(PacienteFilter pacientefilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		// select * from cliente
		// from Cliente
		CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
//		where
		Root<Paciente> root = criteria.from(Paciente.class);
		From<?, ?> logJoin = root.join("logs", JoinType.LEFT);
		criteria.distinct(true);
		List<Order> orderList = new ArrayList();
		orderList.add(builder.desc(root.get("id")));
		criteria.orderBy(orderList);
		

		Predicate[] predicates = criarRestricoes(pacientefilter, builder, root, logJoin);
		criteria.where(predicates);

		TypedQuery<Paciente> query = manager.createQuery(criteria);
		List<Paciente> listacarga = new ArrayList<>();
		listacarga = query.getResultList();
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, listacarga.size());

	}

	private Predicate[] criarRestricoes(PacienteFilter pacientefilter, CriteriaBuilder builder, Root<Paciente> root,
			From<?, ?> logJoin) {
		List<Predicate> predicates = new ArrayList<>();
		
		Tenant t = tenantUsuario.buscarOuFalhar();
		predicates.add(builder.equal(builder.lower(root.get("tenant")), t));
		if (pacientefilter.getId() != null) {
			
			try {
				Integer valor = Integer.parseInt(pacientefilter.getId());
				predicates.add(builder.equal(builder.lower(root.get("id")), valor));
			} catch (Exception e) {
				predicates.add(builder.equal(builder.lower(root.get("id")), 0));
			}
			
		}

		if (pacientefilter.getCpf() != null) {
			predicates.add(builder.like(builder.lower(root.get("cpf")), "%" + pacientefilter.getCpf() + "%"));
		}
		if (pacientefilter.getNome() != null) {
			predicates.add(
					builder.like(builder.lower(root.get("nome")), "%" + pacientefilter.getNome() + "%"));
		}
	
		if (pacientefilter.getDatanascde() != null) {
//			Instant instant = pacientefilter.getDatanascde().toInstant();
//			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
//			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.greaterThanOrEqualTo(root.get("datanasc"), pacientefilter.getDatanascde()));
		}
		if (pacientefilter.getDatanascate() != null) {
//			Instant instant = pacientefilter.getDatanascate().toInstant();
//			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
//			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.lessThanOrEqualTo(root.get("datanasc"), pacientefilter.getDatanascate()));
		}
		
		if (pacientefilter.getSexo() != null) {
			predicates.add(
					builder.like(builder.lower(root.get("sexo")), "%" + pacientefilter.getSexo() + "%"));
		}

		if (pacientefilter.getEmailusuario() != null) {
			predicates.add(builder.like(builder.lower(logJoin.get("emailUsuario")),
					"%" + pacientefilter.getEmailusuario() + "%"));
		}
		
		if (pacientefilter.getDatagravacaode() != null) {
			Instant instant = pacientefilter.getDatagravacaode().toInstant();
			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.greaterThanOrEqualTo(logJoin.get("datagravacao"), offsetDateTime));
		}
		if (pacientefilter.getDatagravacaoate() != null) {
			Instant instant = pacientefilter.getDatagravacaoate().toInstant();
			ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
			OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
			predicates.add(builder.lessThanOrEqualTo(logJoin.get("datagravacao"), offsetDateTime));
		}

		if (pacientefilter.getStatus().equals("Ativos")) {
			predicates.add(builder.equal(builder.lower(root.get("status")), true));
		} else {
			predicates.add(builder.equal(builder.lower(root.get("status")), false));
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

	private Long total(PacienteFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Paciente> root = criteria.from(Paciente.class);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
