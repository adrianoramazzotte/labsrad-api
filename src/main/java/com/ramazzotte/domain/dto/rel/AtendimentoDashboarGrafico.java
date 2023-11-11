package com.ramazzotte.domain.dto.rel;

import java.util.ArrayList;
import java.util.List;

public class AtendimentoDashboarGrafico {
	
	List<DataSetGrafico> dataset= new ArrayList<DataSetGrafico>();//grafico de barra
	List<String> labels = new ArrayList<String>();//grafico de barra
	List<String> usuarios = new ArrayList<String>();//exames por usuario//usuarios
	List<String> exames = new ArrayList<String>();//exames por valor
	List<String> quantidadeExamePorUsuario = new ArrayList<String>();//exames por valor
	List<String> valorTotalExame = new ArrayList<String>();//exames por valor
	List<String> backgroundColor = new ArrayList<String>();//exames por valor
	
	
	
	public List<String> getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(List<String> backgroundColor) {
		backgroundColor.add("#1abc9c");
		backgroundColor.add("#9b59b6");
		backgroundColor.add("#e74c3c");
		backgroundColor.add("#706fd3");
		backgroundColor.add("#ff8e71");
		backgroundColor.add("#ff8e71");		
		backgroundColor.add("#9f5f80");
		backgroundColor.add("#98acf8");
		backgroundColor.add("#153e90");
		backgroundColor.add("#1abc9c");
		backgroundColor.add("#0e49b5");
		this.backgroundColor = backgroundColor;
	}

	public List<String> getQuantidadeExamePorUsuario() {
		return quantidadeExamePorUsuario;
	}

	public void setQuantidadeExamePorUsuario(List<String> quantidadeExamePorUsuario) {
		this.quantidadeExamePorUsuario = quantidadeExamePorUsuario;
	}
	

	public List<String> getValotTotalExame() {
		return valorTotalExame;
	}

	public void setValorTotalExame(List<String> valorTotalExame) {
		this.valorTotalExame = valorTotalExame;
	}

	public List<String> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}

	public List<String> getExames() {
		return exames;
	}

	public void setExames(List<String> exames) {
		this.exames = exames;
	}

	public AtendimentoDashboarGrafico() {	}
	
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<DataSetGrafico> getDataset() {
		return dataset;
	}

	public void setDataset(List<DataSetGrafico> dataset) {
		this.dataset = dataset;
	}

	public AtendimentoDashboarGrafico(List<DataSetGrafico> dataset, List<String> labels) {
		super();
		this.dataset = dataset;
		this.labels = labels;
	}
	
	
}
