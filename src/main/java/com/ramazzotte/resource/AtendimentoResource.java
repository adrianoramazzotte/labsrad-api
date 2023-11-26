package com.ramazzotte.resource;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramazzotte.domain.dto.flat.AtendimentoFlat;
import com.ramazzotte.domain.dto.flat.AtendimentoItensFlat;
import com.ramazzotte.repository.AtendimentoRepository;
import com.ramazzotte.repository.filter.AtendimentoFilter;
import com.ramazzotte.security.resource.CheckSecurity;
import com.ramazzotte.security.resource.CheckSecurity.Atendimento;
import com.ramazzotte.service.AtendimentoService;
import com.ramazzotte.service.rels.RelAtendimento;
import com.ramazzotte.service.util.Tenantuser;

@RestController
@RequestMapping(value = "/atendimentos")
public class AtendimentoResource {
	@Autowired
	private Tenantuser tenantUsuario;
	@Autowired
	private AtendimentoService service;
	@Autowired
	private AtendimentoRepository repoAtend;
	@Autowired
	private RelAtendimento relservice;
	
	@CheckSecurity.Atendimento.PodeConsultar
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		AtendimentoFlat obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@CheckSecurity.Atendimento.PodeConsultar
	@RequestMapping(value = "/itens/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findItensExames(@PathVariable Integer id) {
		List<AtendimentoItensFlat> obj = service.findItensExames(id);
		return ResponseEntity.ok().body(obj);
	}
 
 
//	@CheckSecurity.Atendimento.PodeConsultar
//	@RequestMapping(method = RequestMethod.GET)
//	public Page<AtendimentoFlat> findAllPag(AtendimentoFilter atendimentoFilter, Pageable pageable) {
//		Page<Atendimento> pacs = repoAtend.filtrar(atendimentoFilter, pageable);
//		Page<AtendimentoFlat> atedflat = service.mudarPacienteParaFlat(pacs);
//		return atedflat;
//	}
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<AtendimentoFlat>> findAllInativo() {
	//	List<AtendimentoDTO> list = service.findAll();
		List<AtendimentoFlat> list = service.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}
	
	
	@CheckSecurity.Atendimento.PodeExcluir
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Atendimento.PodeCadastrar
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AtendimentoFlat> insert(@RequestBody AtendimentoFlat obj) {
		AtendimentoFlat objNovo = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}

	@CheckSecurity.Atendimento.PodeAtualizar
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody AtendimentoFlat obj, @PathVariable Integer id) {
		obj.setId(id);
		AtendimentoFlat atividadeAtualizado = service.from(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(atividadeAtualizado);
		
	}

	
	@RequestMapping(value = "/relatorios/atendimentoFiltro", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> atendimentoFiltro(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim) throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.relatorioAtendimentos(inicio, fim, tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@RequestMapping(value = "/relatorios/mensalUsuario", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> mensalUsuario(
			@RequestParam Integer mes, 
			@RequestParam Integer ano, 
			@RequestParam Integer usuario) throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.mensalUsuario(mes, ano, usuario, tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@RequestMapping(value = "/relatorios/mensalconvenio", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> mensalConvenio(
			@RequestParam Integer mes, 
			@RequestParam Integer ano, 
			@RequestParam Integer convenio) throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.mensalConvenio(mes, ano, convenio, tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@RequestMapping(value = "/relatorios/mensal", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> mensal(
			@RequestParam Integer mes, 
			@RequestParam Integer ano) throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.mensal(mes, ano, tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@RequestMapping(value = "/atendimento/ficha", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> ficha(
			@RequestParam String atendimento) throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.ficha(atendimento, tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@RequestMapping(value = "/relatorios/convenios", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> convenio() throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.convenio(tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@RequestMapping(value = "/relatorios/exames", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> exame() throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.exame(tenant);				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	@RequestMapping(value = "/relatorios/usuarios", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> usuario() throws Exception {
		Integer tenant = tenantUsuario.buscarOuFalharInt();
		byte[] relatorio = relservice.usuario(tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@CheckSecurity.Atendimento.PodeAlterarStatus
	@RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
		service.status(obj,id);		

	}
	
	
}
