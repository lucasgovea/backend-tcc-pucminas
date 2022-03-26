package br.com.tcc.pucminas.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tcc.pucminas.model.Cep;

@FeignClient(name = "cepService", url = "https://api.postmon.com.br")
public interface CepClient {

	 @RequestMapping("/v1/cep/{cep}")
	 Cep getCep(@PathVariable("cep") String cep);

}
