package com.ramazzotte.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UsuarioEmpresa {
	@EmbeddedId
	public UsuarioEmpresaPK id = new UsuarioEmpresaPK();
    public Boolean empresapadrao;
    
    public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer tenantId;
    
	public UsuarioEmpresaPK getId() {
		return id;
	}

	public Boolean getEmpresapadrao() {
		return empresapadrao;
	}

	public void setEmpresapadrao(Boolean empresapadrao) {
		this.empresapadrao = empresapadrao;
	}

	public void setId(UsuarioEmpresaPK id) {
		this.id = id;
	}

	public UsuarioEmpresa(UsuarioEmpresaPK id) {
		super();
		this.id = id;
	}

	public UsuarioEmpresa() {

	}
	
}
