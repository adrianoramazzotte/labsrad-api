package com.ramazzotte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.LogSistema;

@Repository
public interface LogSistemaRepository extends JpaRepository<LogSistema, Integer> {
	
}