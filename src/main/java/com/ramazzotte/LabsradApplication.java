package com.ramazzotte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.reactive.function.client.WebClient;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class LabsradApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabsradApplication.class, args);
	}
	 @Bean
		public WebClient webClientcnpj(WebClient.Builder builder) {
			return builder
				.baseUrl("https://brasilapi.com.br/api/cnpj/v1/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		}

}
