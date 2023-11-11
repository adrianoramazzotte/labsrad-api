package com.ramazzotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ramazzotte.domain.Empresa;





@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
	

	 
	@Query(value= "select * from empresa where id = ?", nativeQuery = true)
	Empresa findPorId(int id);

	@Query(value= "	select max(tenant_id + 1) from empresa", nativeQuery = true)
	Integer tenantMaisUm();
	@Query(value= "select * from empresa where tenant_id = ? and status = 1 order by id desc", nativeQuery = true)
	List<Empresa> findAllSqlT(int id);
	@Query(value= "select * from empresa where gtenant_id = ? and status = 1 order by id desc", nativeQuery = true)
	List<Empresa> findAllSqlG(int id);
	
	@Query(value= "select * from empresa where tenant_id = ? and status = 0 order by id desc", nativeQuery = true)
	List<Empresa> findAllSqlInativosT(int id);
	@Query(value= "select * from empresa where gtenant_id = ? and status = 0 order by id desc", nativeQuery = true)
	List<Empresa> findAllSqlInativosG(int id);

	@Query(value= "select * from empresa where id = ?", nativeQuery = true)
	Empresa find(int id);
	
	@Query(value= "select razao from empresa where id = ?", nativeQuery = true)
	String findRazao(int id);
	@Query(value= "select tenant_id from empresa where id = ?", nativeQuery = true)
	Integer buscarTenant(int idempresa);
	
	@Query(value= "select tenant_id  from empresa where id = ?", nativeQuery = true)
	Integer buscarTenantCadastrado(int idempresa);

	@Query(value= "select * from empresa where gtenant_id in (\r\n"
			+ "SELECT gtenant_id FROM usuario_empresa where id_usuario = ?)", nativeQuery = true)
	List<Empresa> findAllSqlUsuario(Integer id);
	
	@Query(value= "select * from empresa where gtenant_id =?", nativeQuery = true)
	List<Empresa> findAllSqlEmpsPorGtenant(Integer id);
	@Query(value= "select * from empresa where  id in (\r\n"
			+ "select id_empresa from usuario_empresa where id_usuario  = ?)", nativeQuery = true)
	List<Empresa> findAllSqlEmpsPorGtenantComUsuario(Integer idUsuario);
	
	@Query(value= "select * from empresa where id  not in (\r\n"
			+ "select id_empresa from usuario_empresa where id_usuario  = ?)", nativeQuery = true)
	List<Empresa> findAllSqlEmpsNotInGtenantComUsuario(Integer idUsuario);
	
	@Query(value= "select * from empresa where tenant_id in (\r\n"
			+ "SELECT tenant_id FROM usuario_empresa where id_usuario = ?)", nativeQuery = true)
	List<Empresa> findAllSqlEmpUsuario(Integer id);	
	@Query(value= "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
	void setForeykey();
	@Query(value= "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
	void retForeykey();
	
	@Transactional(readOnly = true)
	Empresa findByCpfoucnpj(String cpfoucnpj);
	@Query(value= "select * from empresa where status = 0 order by id desc", nativeQuery = true)
	List<Empresa> findAllInativos();
	@Query(value= "select * from empresa where status = 1 order by id desc", nativeQuery = true)
	List<Empresa> findAllAtivas();
	@Query(value= "select * from empresa where tenant_id = ?", nativeQuery = true)
	Empresa findEmpPorTenantAtivo(Integer tenantativo);




	

	
}
