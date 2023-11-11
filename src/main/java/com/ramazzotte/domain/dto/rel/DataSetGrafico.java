package com.ramazzotte.domain.dto.rel;

import java.util.ArrayList;
import java.util.List;

public class DataSetGrafico {
	
	private String label = "Exames";
	private String backgroundColor = "#42A5F5";
	private String borderColor = "#1E88E5";
    List<String>data = new ArrayList<String>();
    
	public DataSetGrafico(String label, String backgroundColor, String borderColor, List<String> data) {
		super();
		this.label = "Exames";
		this.backgroundColor = "#42A5F5";
		this.borderColor = "#1E88E5";
		this.data =data;
	}

	public DataSetGrafico() {
		
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public List<String> getData() {		
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}




}
