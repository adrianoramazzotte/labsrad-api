package com.ramazzotte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Empresa;
import com.ramazzotte.domain.LogSistema;
import com.ramazzotte.domain.Tenant;
import com.ramazzotte.domain.dto.flat.EmpresaFlat;
import com.ramazzotte.repository.EmpresaRepository;
import com.ramazzotte.repository.LogSistemaRepository;
import com.ramazzotte.repository.TenantRepository;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;
import com.ramazzotte.service.util.Tenantuser;



@Service
public class EmpresaService {

	@Autowired
	private LogSistemaService log;
	@Autowired
	private LogSistemaRepository repolog;

	@Autowired
	private Tenantuser tenantUsuario;

	@Autowired
	private EmpresaRepository repo;
	@Autowired
	private TenantRepository repoTenant;

	
	public Empresa insert(Empresa obj) {
		obj.setId(null);
		Integer tenant = repo.tenantMaisUm();
		Tenant t = new Tenant();		
		t.setId(tenant);
		t.setDescricao(obj.getRazaosocial());
		salvarTenant(t);
		obj.setTenant_id(t.getId());
		repo.save(obj);
		logEmpresa(obj, "inserir");
		return obj;
	}
	
	private void salvarTenant(Tenant t) {		
		repoTenant.save(t);
	}

	private void logEmpresa(Empresa obj, String string) {
		LogSistema logsistema = log.insert(obj, string);
		logsistema.setEmpresa(obj);
		logsistema.setTenant(tenantUsuario.buscarOuFalhar());
		repolog.save(logsistema);

	}

//	public List<EmpresaFlat> findAll() {
//		List<EmpresaFlat> empresasF = new ArrayList<EmpresaFlat>();
//		EmpresaFlat empFlat = new EmpresaFlat(emp);
//			if(emp.getTenant_id() ==tenantUsuario.buscarOuFalharInt()) {
//				empFlat.setEditar(true);
//
//				empFlat.setEditar(true);
//
//			empFlat.setEmpresa(repoTenant.findEmpresaTenant(emp.getTenant_id()));
//			empresasF.add(empFlat);
//
//		}
//
//		return empresasF;
//	}

	public Empresa find(int id) {
		Empresa empresa = repo.find(id);

		return empresa;
	}

	public Empresa from(Empresa empresaNovo) {
		Empresa empresaAtual = buscarOuFalhar(empresaNovo.getId());

		BeanUtils.copyProperties(empresaNovo, empresaAtual, "id");
		empresaAtual.setCidade(empresaAtual.getCidade());
		empresaAtual.setUf(empresaNovo.getUf());
		logEmpresa(empresaAtual, "alterar");
		Tenant t = repoTenant.findPorId(empresaNovo.getTenant_id());
		t.setDescricao(empresaNovo.getRazaosocial());
		
		repoTenant.save(t);

		return repo.save(empresaAtual);
	}

	public Empresa buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("EmpresaLog não encontrado", id)));
	}

//	public List<EmpresaFlat> findAllInativos() {
//		List<EmpresaFlat> empresasF = new ArrayList<EmpresaFlat>();
//		if (tenantUsuario.tenantOuGtenant().equals("tenant_id")) {
//			List<Empresa> empresas = repo.findAllSqlInativosT(tenantUsuario.buscarOuFalhar().getId());
//			for (Empresa emp : empresas) {
//				EmpresaFlat empFlat = new EmpresaFlat(emp);
//				empresasF.add(empFlat);
//			}
//		} else {
//			List<Empresa> empresas = repo.findAllSqlInativosG(tenantUsuario.buscarOuFalharGtenant());
//			for (Empresa emp : empresas) {
//				EmpresaFlat empFlat = new EmpresaFlat(emp);
//				empresasF.add(empFlat);
//			}
//		}
//
//		return empresasF;
//	}

	public List<EmpresaFlat> findAllUsuario() {
		List<Empresa> empresas = repo.findAllSqlEmpUsuario(tenantUsuario.buscarUsuario().getId());
		List<EmpresaFlat> empresasF = new ArrayList<EmpresaFlat>();
		for (Empresa emp : empresas) {
			EmpresaFlat empFlat = new EmpresaFlat(emp);
			empresasF.add(empFlat);
		}

		return empresasF;
	}

	public List<EmpresaFlat> findAll() {
		List<Empresa> emp = repo.findAllAtivas();
		List<EmpresaFlat> listempFlat = new ArrayList<>();
		for(Empresa e: emp) {
			EmpresaFlat empflat = new EmpresaFlat(e);
			listempFlat.add(empflat);
		}
		return listempFlat;
	}

	public List<EmpresaFlat> findAllInativos() {
		List<Empresa> emp = repo.findAllInativos();
		List<EmpresaFlat> listempFlat = new ArrayList<>();
		for(Empresa e: emp) {
			EmpresaFlat empflat = new EmpresaFlat(e);
			listempFlat.add(empflat);
		}
		return listempFlat;
	}

}
