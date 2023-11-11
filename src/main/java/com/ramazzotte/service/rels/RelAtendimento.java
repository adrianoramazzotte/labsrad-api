package com.ramazzotte.service.rels;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.domain.AtendimentoItens;
import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.domain.Usuario;
import com.ramazzotte.domain.dto.rel.AtendimentoDashboard;
import com.ramazzotte.domain.dto.rel.AtendimentoItemFichaRel;
import com.ramazzotte.domain.dto.rel.ConvenioRel;
import com.ramazzotte.domain.dto.rel.ExameRel;
import com.ramazzotte.domain.dto.rel.UsuRel;
import com.ramazzotte.repository.AtendimentoItensRepository;
import com.ramazzotte.repository.AtendimentoRepository;
import com.ramazzotte.repository.ConvenioRepository;
import com.ramazzotte.repository.ExameConvenioRepository;
import com.ramazzotte.repository.ExameRepository;
import com.ramazzotte.repository.UsuarioRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelAtendimento {
	@Autowired
	private AtendimentoRepository repoAtend;
	@Autowired
	private AtendimentoItensRepository repoAtendItens;
	@Autowired
	private ConvenioRepository repoConv;
	@Autowired
	private ExameConvenioRepository repoExameConvenio;

	@Autowired
	private ExameRepository repoExame;
	@Autowired
	private UsuarioRepository repoUsu;
	
	public byte[] relatorioAtendimentos(LocalDate inicio, LocalDate fim, Integer tenant) throws Exception {
		List<Atendimento> dados = repoAtend.porDatas(inicio.toString(), fim.toString(), tenant);
		List<AtendimentoDashboard> atendsRels = new ArrayList<AtendimentoDashboard>();
		for(Atendimento aten: dados) {
			for(AtendimentoItens ai : aten.getItensAtendimento()) {
				AtendimentoDashboard atenRel = new AtendimentoDashboard(ai);
				atendsRels.add(atenRel);
			}		
		}
	        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("dataInicio", Date.valueOf(inicio));
		parametros.put("dataFim", Date.valueOf(fim));
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/atendimentosfiltro.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(atendsRels, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	public byte[] mensalUsuario(Integer mes, Integer ano, Integer usuario,  Integer tenant) throws Exception {
		List<Atendimento> dados = repoAtend.mensalUsuario(mes, ano, usuario, tenant);
		List<AtendimentoDashboard> atendsRels = new ArrayList<AtendimentoDashboard>();
		for(Atendimento aten: dados) {
			for(AtendimentoItens ai : aten.getItensAtendimento()) {
				AtendimentoDashboard atenRel = new AtendimentoDashboard(ai);
				atendsRels.add(atenRel);
			}		
		}
	        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("mes", (mes));
		parametros.put("ano", (ano));
		parametros.put("usuario", (usuario));
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/atendimentosmensalusuario.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(atendsRels, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	public byte[] mensalConvenio(Integer mes, Integer ano, Integer usuario,  Integer tenant) throws Exception {
		List<Atendimento> dados = repoAtend.mensalConvenio(mes, ano, usuario, tenant);
		List<AtendimentoDashboard> atendsRels = new ArrayList<AtendimentoDashboard>();
		for(Atendimento aten: dados) {
			for(AtendimentoItens ai : aten.getItensAtendimento()) {
				AtendimentoDashboard atenRel = new AtendimentoDashboard(ai);
				atendsRels.add(atenRel);
			}		
		}
	        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("mes", (mes));
		parametros.put("ano", (ano));
		parametros.put("usuario", (usuario));
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/atendimentosmensalconvenio.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(atendsRels, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public byte[] mensal(Integer mes, Integer ano, Integer tenant) throws Exception {
		List<Atendimento> dados = repoAtend.mensal(mes, ano, tenant);
		List<AtendimentoDashboard> atendsRels = new ArrayList<AtendimentoDashboard>();
		for(Atendimento aten: dados) {
			for(AtendimentoItens ai : aten.getItensAtendimento()) {
				AtendimentoDashboard atenRel = new AtendimentoDashboard(ai);
				atendsRels.add(atenRel);
			}		
		}
	        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("mes", (mes));
		parametros.put("ano", (ano));
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/atendimentosmensal.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(atendsRels, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}


	public byte[] convenio(Integer tenant) throws Exception {
		List<Convenio> convenios = repoConv.convenio(tenant);
		List<ConvenioRel> convrels = new ArrayList<ConvenioRel>();
		for(Convenio conv: convenios) {
			ConvenioRel convrel = new ConvenioRel(conv);
			convrels.add(convrel);
			}		
		
			        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/convenios.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(convrels, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
	

	}


	public byte[] exame(Integer tenant) throws Exception {
		List<ExameConvenio> exame = repoExameConvenio.exame(tenant);
		List<ExameRel>listexarel = new ArrayList<ExameRel>();
		for (ExameConvenio exa:exame ) {
			ExameRel exarel = new ExameRel(exa);
			listexarel.add(exarel);
		}
			        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/exames.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(listexarel, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
	}


	public byte[] usuario(Integer tenant) throws Exception {
		List<Usuario> listUsu = repoUsu.findAllList(tenant);
		List<UsuRel>listusurel = new ArrayList<UsuRel>();
		for (Usuario usu:listUsu ) {
			UsuRel usurel = new UsuRel(usu);
			listusurel.add(usurel);
		}
//		
			        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/usuarios.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(listusurel, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
	}

	
	public byte[] ficha(String atendimento, Integer tenant) throws Exception {
		int atend = Integer.parseInt(atendimento);
		List<AtendimentoItens> atendimentoItens = repoAtendItens.findPorAtendimento(atendimento, tenant);
		List<AtendimentoItemFichaRel>listatendimentoFicha = new ArrayList<AtendimentoItemFichaRel>();
		for (AtendimentoItens atendimentoItem:atendimentoItens ) {
			AtendimentoItemFichaRel atenFicha = new AtendimentoItemFichaRel(atendimentoItem);
			listatendimentoFicha.add(atenFicha);
		}
		
			        
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("atendimento", (atend));
		parametros.put("tenant", (tenant));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/rel/FichaAtendimento.jasper");		
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(listatendimentoFicha, false);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,jrb);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);		
	
	}
	
	
	

}
