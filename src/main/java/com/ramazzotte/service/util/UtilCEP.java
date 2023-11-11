package com.ramazzotte.service.util;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class UtilCEP {

	private RestTemplate restTemplate=new RestTemplate();
	private String url = "https://viacep.com.br/ws/";
	public UtilCEP() {	}
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
	private static final String RESOURCE_PATH = "/cpf";
	
	

}
