package com.vvelazquez.telegram.rest.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.vvelazquez.telegram.entity.ParametroGeneral;
import com.vvelazquez.telegram.rest.model.CuentasModel;
import com.vvelazquez.telegram.rest.model.Movimientos;
import com.vvelazquez.telegram.rest.model.TokenAcceso;
import com.vvelazquez.telegram.rest.model.TokenModel;
import com.vvelazquez.telegram.rest.repository.ApiBanregioRepository;
import com.vvelazquez.telegram.service.ParametrosGeneralesService;
import com.vvelazquez.telegram.utils.Constantes;
import com.vvelazquez.telegram.utils.Utilidades;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ApiBanregioService {
	
	@Value("${api.banregio.client_id}")
	private String clienteId;
	
	@Value("${api.banregio.client_secret}")
	private String secretId;
	
	@Value("${api.banregio.cuenta}")
	private Long cuentaId;
	
	@Value("${sr-bot.host}")
	private String hostSrBot;
	
	@Autowired
	private ApiBanregioRepository apiBanregioRepository;

	@Autowired
	private ParametrosGeneralesService parametroGeneralesService;
	
	@Autowired
	private Utilidades utilidades;

	public CuentasModel obtenerCuentas() {
		TokenModel tokenAcceso = this.obtenerTokenSesion(parametroGeneralesService.obtener(1L).get());
		parametroGeneralesService.actualizarToken(new ParametroGeneral(1L, tokenAcceso.getToken(), tokenAcceso.getTokenRefresco()));
		return apiBanregioRepository.obtenerCuentas(String.format("Bearer %s", tokenAcceso.getToken()), MediaType.APPLICATION_JSON_VALUE);
	}
	
	public Movimientos obtenerMovimientos() {
		log.info("Actualizando token");
		TokenModel tokenAcceso = this.obtenerTokenSesion(parametroGeneralesService.obtener(1L).get());
		parametroGeneralesService.actualizarToken(new ParametroGeneral(1L, tokenAcceso.getToken(), tokenAcceso.getTokenRefresco()));
		String fecha = utilidades.obtenerFechaConFormato(Calendar.getInstance().getTime(), Constantes.DATE_FORMAT_01);
		log.info("Buscando movimientos");
		return apiBanregioRepository.obtenerMovimientos(String.format("Bearer %s", tokenAcceso.getToken()), MediaType.APPLICATION_JSON_VALUE, cuentaId, fecha, fecha);
	}


	public TokenModel obtenerTokenSesion(ParametroGeneral parametroGeneral) {
		return apiBanregioRepository.obtenerTokenSesion(String.format("Bearer %s", parametroGeneral.getToken()), MediaType.APPLICATION_JSON_VALUE, new TokenAcceso("refresh_token", clienteId, secretId, parametroGeneral.getTokenRefresh()));
	}

	
	
	
}
