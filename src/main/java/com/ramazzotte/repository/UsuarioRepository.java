package com.ramazzotte.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ramazzotte.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query(value= "select * from usuario where status=1 and email = ?", nativeQuery = true)
	Usuario findPorEmail(String email);
	
	@Query(value= "select * from usuario where id = ?", nativeQuery = true)
	Usuario findPorId(int id);
	
	Optional<Usuario> findByEmail(String email);
	
	@Query(value = "select * from usuario", nativeQuery = true)
	List<Usuario> findAllSql();	
	

	@Query(value = "select * from usuario where status=0 and id !=3 and id !=1 and tenant_id = ?", nativeQuery = true)
	List<Usuario> findAllSqlInativo(int id);
	@Modifying
	@Transactional
	@Query(value = "UPDATE usuario SET senha = ? WHERE id = ?;", nativeQuery = true)
	void saveSenha(String senha, int id);
	
	@Query(value = "Select * from usuario WHERE email = ?;", nativeQuery = true)
	Usuario buscarUsuario(String email);
	@Query(value = "Select * from usuario WHERE id = ?;", nativeQuery = true)
	Usuario buscarUsuarioId(int id);
	@Query(value = "Select * from usuario WHERE id !=3 and id !=1 and tenant_id = ?", nativeQuery = true)
	List<Usuario> findAllList(Integer tenant);

	
	@Query(value= "select tenantativo from usuario where email=?", nativeQuery = true) 
	Integer buscarTenant(String email);

	@Query(value= "select gtenant_id from usuario where email=?", nativeQuery = true) 
	Integer buscarGTenant(String email);

	@Query(value= "select gtenant_id from usuario where email=?", nativeQuery = true) 
	Integer buscarGTenantId(String email);
	@Query(value= "select gtenant_id from usuario where email=?", nativeQuery = true) 
	Integer findPorGtwenantPeloEmail(String email);

	@Modifying
	@Transactional
	@Query(value = "UPDATE usuario SET tenantativo = ? WHERE id = ?", nativeQuery = true)
	void settenantAtivo(Integer tenanttenantativo, Integer idusuario);



	@Modifying
	@Transactional
	@Query(value = "UPDATE usuario SET gtenantativo = ? WHERE id = ?", nativeQuery = true)
	void setGtenantAtivo(Integer gtenant, Integer id);
	@Modifying
	@Transactional
	@Query(value = "UPDATE usuario SET tenantativo = tenant_id WHERE email = ?", nativeQuery = true)
	void voltartenant(String buscarEmailUsuToken);


	
}
