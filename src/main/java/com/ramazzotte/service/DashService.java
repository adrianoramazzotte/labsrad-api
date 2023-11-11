
package com.ramazzotte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.dto.rel.AtendimentoDashboarGrafico;
import com.ramazzotte.domain.dto.rel.DataSetGrafico;
import com.ramazzotte.repository.ClassepermissaoRepository;
import com.ramazzotte.service.util.Tenantuser;


@Service
public class DashService {
	@Autowired
	private Tenantuser tenantUsuario;

	@Autowired
	private ClassepermissaoRepository repoclassePermissaoRepository;

	public AtendimentoDashboarGrafico buscar() {
		AtendimentoDashboarGrafico atDashGrafico = new AtendimentoDashboarGrafico();
		List<DataSetGrafico> dtlist = new ArrayList<DataSetGrafico>();
		DataSetGrafico datset = new DataSetGrafico();
		List<String>data = new ArrayList<String>();
		List<String>objsExameAno = (repoclassePermissaoRepository.buscarExamePorMesAno(tenantUsuario.buscarOuFalharInt()));
		List<String>meses = new ArrayList<String>();
		for(String obj:objsExameAno) {
			String[] textoSeparado = obj.split(",");
			meses.add(textoSeparado[1]+"/"+textoSeparado[2]);
			data.add(textoSeparado[0]);			
		}
		datset.setData(data);
		atDashGrafico.setLabels(meses);
		datset.setBackgroundColor("#42A5F5");
		datset.setBorderColor("#1E88E5");
		datset.setLabel("Exames");
		dtlist.add(datset);
		atDashGrafico.setDataset(dtlist);
		List<String>objs = (repoclassePermissaoRepository.buscarExames(tenantUsuario.buscarOuFalharInt()));
		List<String>objusuarios = (repoclassePermissaoRepository.buscarExameProUsuario(tenantUsuario.buscarOuFalharInt()));
		List<String>exames = new ArrayList<String>();
		List<String>valorTotalCadaExames = new ArrayList<String>();
		List<String>usuarios = new ArrayList<String>();
		List<String>quantiadeExameusuario = new ArrayList<String>();
		for(String obj:objs) {
			String[] textoSeparado = obj.split(",");
			exames.add(textoSeparado[1]);
			valorTotalCadaExames.add(textoSeparado[0]);
		}
		for(String obj:objusuarios) {
			String[] textoSeparado = obj.split(",");
			usuarios.add(textoSeparado[1]);
			quantiadeExameusuario.add(textoSeparado[0]);
		}
		atDashGrafico.setUsuarios(usuarios);
		atDashGrafico.setQuantidadeExamePorUsuario(quantiadeExameusuario);
		atDashGrafico.setExames(exames);
		atDashGrafico.setValorTotalExame(valorTotalCadaExames);
		return atDashGrafico;
	}



	
}
