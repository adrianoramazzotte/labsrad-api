package com.ramazzotte.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.UsuarioEmpresa;



@Repository
public interface UsuarioEmpresaRepository extends JpaRepository<UsuarioEmpresa, Integer> {
	@Query(value= "select empresapadrao from usuario_empresa where id_empresa = ? and id_usuario = ?", nativeQuery = true)
	Integer verificaEmpPadrao(Integer id, Integer id2);
	@Query(value= "select id_usuario from usuario_empresa where id_empresa = ? and id_usuario = ?", nativeQuery = true)
	Integer verificaEmpUsuario(Integer id, Integer id2);
    @Modifying
    @Transactional
	@Query(value= "delete from usuario_empresa where id_usuario = ?", nativeQuery = true)
	void deleteEmpPorUsuario(Integer id);
	
	


	
}
