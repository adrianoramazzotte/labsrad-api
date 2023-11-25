package com.ramazzotte.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ramazzotte.domain.Codigo;
import com.ramazzotte.domain.dto.CnpjDTOAPI;
import com.ramazzotte.repository.CodigoRepository;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;

import reactor.core.publisher.Mono;




@Service
public class SevicosResquestAllAIP {
	
	@Autowired
	private WebClient webClientcnpj;
	@Autowired
	private CodigoRepository repoCodigo;
	
//	@Autowired
//	private WebClient webClientnfe;
	
	public CnpjDTOAPI buscarcnpj(String cnpjfront) {

		Mono<CnpjDTOAPI> cnpj = this.webClientcnpj
			.method(HttpMethod.GET)
			.uri("/{codigo}", cnpjfront)
			.retrieve()
			.bodyToMono(CnpjDTOAPI.class);

		CnpjDTOAPI cnpjInfo = cnpj.block();
		return cnpjInfo;
	}

	public String enviarSms(String telefone) {
		int min = 10000;
		int max = 99999;
		int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

//		Mono<String> cnpj = this.webClient.method(HttpMethod.GET)
//				.uri(config solicitada " + random_int)
//				.retrieve().bodyToMono(String.class);
//
//		String resposta = cnpj.block();
//		String resposta = "OK";	

//		String originalInput = String.valueOf(random_int);
//		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//		String decodedString = new String(decodedBytes);

		Codigo codigobanco = repoCodigo.findPorTelefone(telefone);
		if (codigobanco == null) {
			Codigo c = new Codigo();
			c.setTelefona(telefone);
			c.setCodigo(String.valueOf(random_int));
			repoCodigo.save(c);
		} else {
			codigobanco.setCodigo(String.valueOf(random_int));
			repoCodigo.save(codigobanco);
		}

//		if (!resposta.equals("OK")) {
//			return resposta;
////			return String.valueOf(random_int);
//
//		} else {
//			return resposta;
//		}
		return null;

	}
	public Codigo validaSMSs(Codigo obj1) {
		String resposta = "";
		Codigo codigobanco = repoCodigo.findByTelefone(obj1.getTelefone());

		if (codigobanco == null) {
			codigobanco = buscarOuFalhar(0);

		} else {
			if (!codigobanco.getCodigo().equals(obj1.getCodigo())) {
				codigobanco = buscarOuFalhar1(0);

			}
		}
		return codigobanco;
	}
	public Codigo buscarOuFalhar(int id) {
		return repoCodigo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Número não encontrado", id)));
	}

	private Codigo buscarOuFalhar1(int id) {
		return repoCodigo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Código inválido", id)));
	}

	public Codigo buscarOuFalhar2(int id) {
		return repoCodigo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Telefone já cadastrado", id)));
	}
	


}
