
package com.ramazzotte.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {

	@Modifying
	@Transactional	
    @Query(value= "DELETE FROM usuario_permissao WHERE id_usuario = ?", nativeQuery = true)
  	void deletaPorUsuario(int idusuario);

	
}
