package com.vvelazquez.telegram.rest.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.vvelazquez.telegram.rest.model.CuentasModel;
import com.vvelazquez.telegram.rest.model.Movimientos;
import com.vvelazquez.telegram.rest.model.TokenAcceso;
import com.vvelazquez.telegram.rest.model.TokenModel;

@FeignClient(name="api-banregio", url= "${api.banregio.url}")
public interface ApiBanregioRepository {

	@GetMapping("/v1/accounts/")
	CuentasModel obtenerCuentas(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestHeader(HttpHeaders.ACCEPT) String accept);
	
	@PostMapping("/oauth/authorize")
	void obtenerCodigoAuth(@RequestParam("response_type") String responseType, @RequestParam("client_id") String clientId);

	@PostMapping("/oauth/token/")
	TokenModel obtenerTokenSesion(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestHeader(HttpHeaders.ACCEPT)  String accept, @RequestBody TokenAcceso tokenAcceso);

	@GetMapping("/v1/accounts/{id}/transactions/")
	Movimientos obtenerMovimientos(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestHeader(HttpHeaders.ACCEPT) String accept, 
			@PathVariable("id") Long idCuenta, @RequestParam("min_date") String fechaInicio, @RequestParam("max_date") String fechaFinal);
}
