package com.ramazzotte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.ramazzotte.domain.dto.flat.PermissaoFront;
import com.ramazzotte.domain.dto.flat.UsuarioFlat;
import com.ramazzotte.domain.dto.viewretorno.UsuarioView;

@Entity

public class Usuario implements Serializable {
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", status=" + status + ", nome=" + nome + ", login=" + login + ", email=" + email
				+ ", permissoes=" + permissoes + ", senha=" + senha + "]";
	}
	private static final long serialVersionUID = 1L;
	@JsonView(UsuarioView.Resumo1.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonView(UsuarioView.Resumo1.class)
	private Boolean status = Boolean.TRUE;
	@JsonView(UsuarioView.Resumo1.class)
	private String nome;
	@JsonView(UsuarioView.Resumo1.class)
	private String login;
	@JsonView(UsuarioView.Resumo1.class)
	private String email;
	private String telefone;

	
	private Integer tenantativo;
	private Integer gtenantativo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	private List<Permissao> permissoes= new ArrayList<Permissao>();

    @JsonIgnore
	private String senha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getTenantativo() {
		return tenantativo;
	}

	public void setTenantativo(Integer tenantativo) {
		this.tenantativo = tenantativo;
	}

	public Integer getGtenantAtivo() {
		return gtenantativo;
	}

	public void setGtenantativo(Integer gtenantativo) {
		this.gtenantativo = gtenantativo;
	}


	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}



	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario(Integer id, Boolean status, String nome, String login, String email, List<Permissao> permissoes,
			List<LogSistema> logs, String senha) {
		this.id = id;
		this.status = status;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.permissoes = permissoes;
		this.logs = logs;
		this.senha = senha;
	}

	public Usuario() {
	}

	public Usuario(Usuario usuAtual, UsuarioFlat obj) {
		this.id = usuAtual.getId();
		this.status = obj.getStatus();
		this.nome = obj.getNome();
		this.login = obj.getLogin();
		this.email = obj.getEmail();
		this.senha = usuAtual.getSenha();
		transformarPermisaoFlat(obj.getPermissoes());

	}
	public Usuario(Usuario usuAtual, UsuarioFlat obj, Tenant t) {
		this.id = usuAtual.getId();
		this.status = obj.getStatus();
		this.nome = obj.getNome();
		this.login = obj.getLogin();
		this.email = obj.getEmail();
		this.senha = usuAtual.getSenha();
		transformarPermisaoFlat(obj.getPermissoes());

	}

	private void transformarPermisaoFlat(List<PermissaoFront> permissoes2) {
		for (PermissaoFront pf : permissoes2) {
			switch (pf.getNome()) {
			case "Atendimento":classePermissaoFlatAtendimento(pf);break;
			case "Exame":classePermissaoFlatExame(pf);	break;
			case "Convênio":classePermissaoFlatConvenio(pf);break;
			case "Paciente":classePermissaoFlatPaciente(pf);break;
			case "Usuario":classePermissaoFlatUsuario(pf);	break;
			case "Relatório":classePermissaoFlatRelatorio(pf);break;
			case "Empresa":classePermissaoFlatEmpresa(pf);	break;
			case "Patrimônio":classePermissaoFlatPatrimonio(pf);break;
			}

		}

	}
	private void classePermissaoFlatPatrimonio(PermissaoFront pf) {
		if (pf.getPermission().getCreate()) {	Permissao c  = new Permissao(37,"C_PAT");this.permissoes.add(c);};		
		if (pf.getPermission().getUpdate()) {	Permissao u  = new Permissao(38,"U_PAT");this.permissoes.add(u);};		
		if (pf.getPermission().getDelete()) {	Permissao d  = new Permissao(39,"D_PAT");this.permissoes.add(d);};		
		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(40,"R_PAT");this.permissoes.add(r);};		
		if (pf.getPermission().getStatus()) {	Permissao s  = new Permissao(41,"S_PAT");this.permissoes.add(s);};		
		
	}

	private void classePermissaoFlatEmpresa(PermissaoFront pf) {
		if (pf.getPermission().getCreate()) {	Permissao c  = new Permissao(32,"C_EMP");this.permissoes.add(c);};		
		if (pf.getPermission().getUpdate()) {	Permissao u  = new Permissao(33,"U_EMP");this.permissoes.add(u);};		
		if (pf.getPermission().getDelete()) {	Permissao d  = new Permissao(34,"D_EMPD");this.permissoes.add(d);};		
		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(35,"R_EMP");this.permissoes.add(r);};		
		if (pf.getPermission().getStatus()) {	Permissao s  = new Permissao(36,"S_EMP");this.permissoes.add(s);};		
		
	}

	private void classePermissaoFlatAtendimento(PermissaoFront pf) {
			
		if (pf.getPermission().getCreate()) {	Permissao c  = new Permissao(1,"C_ATEND");this.permissoes.add(c);};		
		if (pf.getPermission().getUpdate()) {	Permissao u  = new Permissao(2,"U_ATEND");this.permissoes.add(u);};		
		if (pf.getPermission().getDelete()) {	Permissao d  = new Permissao(3,"D_ATEND");this.permissoes.add(d);};		
		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(4,"R_ATEND");this.permissoes.add(r);};		
		if (pf.getPermission().getStatus()) {	Permissao s  = new Permissao(5,"S_ATEND");this.permissoes.add(s);};		
	}
	
	private void classePermissaoFlatConvenio(PermissaoFront pf) {
		if (pf.getPermission().getCreate()) {	Permissao c  = new Permissao(6,"C_CONV");this.permissoes.add(c);};		
		if (pf.getPermission().getUpdate()) {	Permissao u  = new Permissao(7,"U_CONV");this.permissoes.add(u);};		
		if (pf.getPermission().getDelete()) {	Permissao d  = new Permissao(8,"D_CONV");this.permissoes.add(d);};		
		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(9,"R_CONV");this.permissoes.add(r);};		
		if (pf.getPermission().getStatus()) {	Permissao s  = new Permissao(10,"S_CONV");this.permissoes.add(s);};	
	      
	}
	
	private void classePermissaoFlatExame(PermissaoFront pf) {	
	
		if (pf.getPermission().getCreate()) {	Permissao c  = new Permissao(11,"C_EXME");this.permissoes.add(c);};		
		if (pf.getPermission().getUpdate()) {	Permissao u  = new Permissao(12,"U_EXME");this.permissoes.add(u);};		
		if (pf.getPermission().getDelete()) {	Permissao d  = new Permissao(13,"D_EXME");this.permissoes.add(d);};		
		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(14,"R_EXME");this.permissoes.add(r);};		
		if (pf.getPermission().getStatus()) {	Permissao s  = new Permissao(15,"S_EXME");this.permissoes.add(s);};	
	}
	
	private void classePermissaoFlatPaciente(PermissaoFront pf) {
		
		if (pf.getPermission().getCreate()) {	Permissao c  = new Permissao(16,"C_PCTE");this.permissoes.add(c);};		
		if (pf.getPermission().getUpdate()) {	Permissao u  = new Permissao(17,"U_PCTE");this.permissoes.add(u);};		
		if (pf.getPermission().getDelete()) {	Permissao d  = new Permissao(18,"D_PCTE");this.permissoes.add(d);};		
		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(19,"R_PCTE");this.permissoes.add(r);};		
		if (pf.getPermission().getStatus()) {	Permissao s  = new Permissao(20,"S_PCTE");this.permissoes.add(s);};	
	}
	private void classePermissaoFlatUsuario(PermissaoFront pf) {
		
		if (pf.getPermission().getCreate()) {	Permissao c  = new Permissao(21,"C_USU");this.permissoes.add(c);};		
		if (pf.getPermission().getUpdate()) {	Permissao u  = new Permissao(22,"U_USU");this.permissoes.add(u);};		
		if (pf.getPermission().getDelete()) {	Permissao d  = new Permissao(23,"D_USU");this.permissoes.add(d);};		
		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(24,"R_USU");this.permissoes.add(r);};		
		if (pf.getPermission().getStatus()) {	Permissao s  = new Permissao(25,"S_USU");this.permissoes.add(s);};			
	}
	private void classePermissaoFlatRelatorio(PermissaoFront pf) {	

		if (pf.getPermission().getRead())   {	Permissao r  = new Permissao(31,"R_REL");this.permissoes.add(r);};	

	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<LogSistema> logs = new ArrayList<LogSistema>();

	public void addLogs(LogSistema log) {
		logs.add(log);
	}

	public LogSistema getLogs() {
        Integer codigo = 0;
		Integer indice = -1;
		LogSistema ultimo = new LogSistema();
		for (int i = 0; i < logs.size(); i++) {
			if (codigo < logs.get(i).getId()) {
				codigo = logs.get(i).getId();
				indice = i;
			}
		}
		if (indice==-1) {
			return ultimo;
		}else {
			return ultimo = logs.get(indice);
		}
		
	}

	public void setLogs(List<LogSistema> logs) {
		this.logs = logs;
	}

	@ManyToOne
	private Tenant tenant;
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	

}