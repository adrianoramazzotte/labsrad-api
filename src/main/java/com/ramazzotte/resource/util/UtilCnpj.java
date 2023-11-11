package com.ramazzotte.resource.util;
import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ramazzotte.domain.dto.CnpjDTOAPI;

@Service
public class UtilCnpj {
	private RestTemplate restTemplate=new RestTemplate();
	private String url = "https://www.receitaws.com.br/v1/cnpj/";
	public UtilCnpj() {	}
	public RestTemplate getRestTemplate() {
		return restTemplate;	}
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;}
	public String getUrl() {
		return url;	}
	public void setUrl(String url) {
		this.url = url;	}
	public static String getResourcePath() {
		return RESOURCE_PATH;	}
	private static final String RESOURCE_PATH = "/cnpj";	
	public CnpjDTOAPI recuperarDadosCnpj(String cnpj) {
		URI resourceUri = URI.create(url+cnpj);
		CnpjDTOAPI cnpjDTO = restTemplate.getForObject
				(resourceUri, CnpjDTOAPI.class);		
		return cnpjDTO;
	}   
}
