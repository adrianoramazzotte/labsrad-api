package com.ramazzotte.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ramazzotte.domain.dto.CnpjDTOAPI;

import reactor.core.publisher.Mono;




@Service
public class SevicosResquestAllAIP {
	
	@Autowired
	private WebClient webClientcnpj;
	
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
	
//	public ProdutoComPreco obterPorCodigoSincrono(Long codigoProduto) {
//
//		Mono<ProdutoComPreco> monoProduto = this.webClientProdutos
//			.method(HttpMethod.GET)
//			.uri("/produtos/{codigo}", codigoProduto)
//			.retrieve()
//			.bodyToMono(ProdutoComPreco.class);
//	
//		Mono<ProdutoComPreco> monoPreco = this.webClientPrecos
//				.method(HttpMethod.GET)
//				.uri("/precos/{codigo}", codigoProduto)
//				.retrieve()
//				.bodyToMono(ProdutoComPreco.class);
//		
//		ProdutoComPreco produto = monoProduto.block();
//		ProdutoComPreco preco = monoPreco.block();
//
//		produto.setPreco(preco.getPreco());
//
//		return produto;
//	}
//	
//	public ProdutoComPreco criar(ProdutoComPreco produtoComPreco) {
//
//		Mono<ProdutoComPreco> monoProduto = 
//				this.webClientProdutos
//					.post()
//					.uri("/produtos")
//					.body(BodyInserters.fromValue(produtoComPreco))
//					.retrieve()
//					.bodyToMono(ProdutoComPreco.class);
//
//		return monoProduto.block();
//	}

}
