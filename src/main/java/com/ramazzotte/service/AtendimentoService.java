package com.ramazzotte.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.domain.AtendimentoItens;
import com.ramazzotte.domain.AtendimentoItensPK;
import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.LogSistema;
import com.ramazzotte.domain.Usuario;
import com.ramazzotte.domain.dto.flat.AtendimentoFlat;
import com.ramazzotte.domain.dto.flat.AtendimentoItensFlat;
import com.ramazzotte.repository.AtendimentoItensRepository;
import com.ramazzotte.repository.AtendimentoRepository;
import com.ramazzotte.repository.ConvenioRepository;
import com.ramazzotte.repository.ExameRepository;
import com.ramazzotte.repository.LogSistemaRepository;
import com.ramazzotte.repository.UsuarioRepository;
import com.ramazzotte.security.DaringSecurity;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;
import com.ramazzotte.service.util.Tenantuser;


@Service
public class AtendimentoService {
	@Autowired
	private LogSistemaService log;
	@Autowired
	private LogSistemaRepository repolog;

	@Autowired
	private DaringSecurity unirad;
	@Autowired
	private ConvenioRepository repoconvenio;
	@Autowired
	private ExameRepository repoexame;
	@Autowired
	private AtendimentoRepository repo;
	@Autowired
	private AtendimentoItensRepository repoitens;
	@Autowired
	private UsuarioRepository repousu;
	@Autowired
	private Tenantuser tenantUsuario;

	public AtendimentoFlat find(Integer id) {
		Atendimento obj = repo.findPorId(id);
		AtendimentoFlat af = new AtendimentoFlat(obj);
		return af;
	}
//	@Transactional
	public void delete(Integer id) {	
		find(id);
		try {			
			repo.deleteById(id);	
			Atendimento ex = new Atendimento();
			ex.setId(id);
			logAtendimento(ex, "excluir");
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir Atendimento ");
		}
	}

	@Transactional
	public AtendimentoFlat insert(AtendimentoFlat obj) {
		Date date = new Date();		
		LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
		Date dateInstante = new Date();
		String email = unirad.getUsuario();
		Usuario usu = repousu.buscarUsuario(email);
		Atendimento atendimento = new Atendimento();
		atendimento.setPaciente(obj.getPaciente());
		atendimento.setId(null);
		atendimento.setTenant(tenantUsuario.buscarOuFalhar());
		atendimento.setIdFicha(obj.getPaciente().getId());
		atendimento.setStatus(true);
		atendimento.setUsuario(usu);
		atendimento.setDatalancamento(dateInstante);
		atendimento.setFormapagamento(obj.getFormapagamento());
		BigDecimal desconto = new BigDecimal(0.0);
		BigDecimal valorTotal = new BigDecimal(0.0);
		if(atendimento.getTenant()!=null) {
			repo.save(atendimento);	
			int codigo = 1;
			Integer acesso;
			String acessoString = repoitens.buscarAcessoMaisUm();
			if(acessoString == null) {
				acesso = 10000;
			}else {
				acesso = Integer.parseInt(acessoString);
			}		
			for (AtendimentoItensFlat ipp : obj.getItensatendimento()) {					
				Convenio conv = repoconvenio.findByCodigo(ipp.getIdconvenio());	
				Exame exame = repoexame.findByCodigo(ipp.getIdexame());
				AtendimentoItensPK atendChave = new AtendimentoItensPK(exame,conv,atendimento, codigo);	
				codigo++;
				AtendimentoItens atenditens = new AtendimentoItens();
				atenditens.setId(atendChave);
				atenditens.setDataAtendimento(localDate);
				atenditens.setStatus(true);
				atenditens.setAcesso(acesso);
				atenditens.setPreco(ipp.getPreco());
				atenditens.setDesconto(ipp.getDesconto());
				atenditens.setTotal(ipp.getTotal());
				atenditens.setTenant(atendimento.getTenant());  
				if(atenditens.getTenant()==null) {					
				}else {
					repoitens.save(atenditens);		
				}
				
				atendimento.addItens(atenditens);
				acesso++;			
				desconto = desconto.add(ipp.getDesconto());
				valorTotal = valorTotal.add(ipp.getPreco());	
				}					    
			atendimento.setDesconto(desconto);
			valorTotal = valorTotal.subtract(desconto);
			atendimento.setTotal(valorTotal);
			logAtendimento(atendimento, "inserir");
		}
		
		
		return obj;
	}
	
	private void logAtendimento(Atendimento obj, String string) {
		LogSistema logsistema = log.insert(obj, string);
		logsistema.setAtendimento(obj);
		repolog.save(logsistema);
		
	}
	
		
	public List<AtendimentoFlat> findAllSQL() {
		List<Atendimento> atendimentos = repo.findAllSql(tenantUsuario.buscarOuFalharInt());
		List<AtendimentoFlat> atendimentosFlat = new ArrayList<>();
		for (Atendimento obj : atendimentos) {
			AtendimentoFlat atendimentoFlat = new AtendimentoFlat(obj);
			
			atendimentosFlat.add(atendimentoFlat);
		}
		return atendimentosFlat;

	}
	
	
	public List<AtendimentoFlat> findAllSqlInativo() {
		List<Atendimento> atendimentos =  repo.findAllSqlInativo(tenantUsuario.buscarOuFalharInt());
		List<AtendimentoFlat> atendimentosFlat = new ArrayList<>();
		for (Atendimento obj : atendimentos) {
			AtendimentoFlat atendimentoFlat = new AtendimentoFlat(obj);
			atendimentosFlat.add(atendimentoFlat);
		}
		return atendimentosFlat;
	}


	public Page<Atendimento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Atendimento buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Atendimento não encontrada", id)));
	}

	public AtendimentoFlat from(AtendimentoFlat obj) {	
		Date date = new Date();		
		LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
    	Atendimento atendimento = new Atendimento();
		atendimento.setPaciente(obj.getPaciente());
		atendimento.setTenant(obj.getTenant());
		atendimento.setId(obj.getId());
		atendimento.setIdFicha(obj.getIdficha());
		atendimento.setFormapagamento(obj.getFormapagamento());
		atendimento.setStatus(obj.getStatus());
		atendimento.setUsuario(obj.getUsuario());
		atendimento.setTenant(tenantUsuario.buscarOuFalhar());
		atendimento.setDatalancamento(obj.getDatalancamento());
		BigDecimal desconto = new BigDecimal(0.0);
		BigDecimal valorTotal = new BigDecimal(0.0);
		repo.save(atendimento);
		Integer acesso;
		String acessoString = repoitens.buscarAcessoMaisUm();
		if(acessoString == null) {
			acesso = 10000;
		}else {
			acesso = Integer.parseInt(acessoString);
		}
		
		int codigo = 1;
		repoitens.deleteByIdAtendimento(atendimento.getId());
		for (AtendimentoItensFlat ipp : obj.getItensatendimento()) {					
			Convenio conv = repoconvenio.findByCodigo(ipp.getIdconvenio());	
			Exame exame = repoexame.findByCodigo(ipp.getIdexame());
			AtendimentoItensPK atendChave = new AtendimentoItensPK(exame,conv,atendimento, codigo);	
			codigo++;
			AtendimentoItens atenditens = new AtendimentoItens();
			atenditens.setId(atendChave);
			if(ipp.getDataatendimento() == null) {
				atenditens.setDataAtendimento(localDate);
			}else {
				atenditens.setDataAtendimento(ipp.getDataatendimento());
			}
			atenditens.setTenant(obj.getTenant());
			atenditens.setStatus(true);
			atenditens.setPreco(ipp.getPreco());
			atenditens.setDesconto(ipp.getDesconto());
			atenditens.setTotal(ipp.getTotal());
			if(ipp.getAcesso()==null) {	
				atenditens.setAcesso(acesso);
				acesso++;
			}else {
				atenditens.setAcesso(ipp.getAcesso());
			}
			repoitens.save(atenditens);		
			atendimento.addItens(atenditens);			
			desconto = desconto.add(ipp.getDesconto());
			valorTotal = valorTotal.add(ipp.getTotal());
			
						
			}					    
		atendimento.setDesconto(desconto);
	//	valorTotal = valorTotal.subtract(desconto);
		atendimento.setTotal(valorTotal);
		repo.save(atendimento);
		return obj;

	}

	@Transactional
	public void status(Boolean obj, int id) {
		Atendimento atend = buscarOuFalhar(id);
		atend.setStatus(obj);
		logAtendimento(atend, "status");
		
	}

	public List<AtendimentoFlat> findAllSqlRel(String inicio, String fim) {
		
		List<Atendimento> atendimentos =  repo.findAllSqlRelDataIniFin(inicio, fim);
		List<AtendimentoFlat> atendimentosFlat = new ArrayList<>();
		for (Atendimento obj : atendimentos) {
			AtendimentoFlat atendimentoFlat = new AtendimentoFlat(obj);
			atendimentosFlat.add(atendimentoFlat);
		}
		return null;
	}
	public List<AtendimentoItensFlat> findItensExames(Integer id) {
	
	   List<AtendimentoItens>  listItens = 	repoitens.findPorAtendimentoID(id);
	   List<AtendimentoItensFlat>  listItenFlat = 	new ArrayList<>();
	   for(AtendimentoItens itens: listItens) {
		   AtendimentoItensFlat itemFlat  = new AtendimentoItensFlat(itens);
		   listItenFlat.add(itemFlat);
	   }
	   return listItenFlat;


	}
	public Page<AtendimentoFlat> mudarPacienteParaFlat(Page<Atendimento> atend) {
		List<AtendimentoFlat> cFlats = new ArrayList<AtendimentoFlat>();

		for (Atendimento p : atend.getContent()) {
			AtendimentoFlat cFlat = new AtendimentoFlat(p, "control");
			cFlats.add(cFlat);

		}
		Page<AtendimentoFlat> page = new PageImpl<>(cFlats, atend.getPageable(),
				atend.getTotalElements());

		return page;
	}


}
