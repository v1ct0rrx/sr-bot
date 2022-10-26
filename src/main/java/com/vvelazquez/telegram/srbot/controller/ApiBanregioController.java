package com.vvelazquez.telegram.srbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vvelazquez.telegram.rest.model.Movimientos;
import com.vvelazquez.telegram.rest.service.ApiBanregioService;

@RestController
@RequestMapping("banregio/")
public class ApiBanregioController {

	@Autowired
	private ApiBanregioService apiBanregioService;
	
	@GetMapping("cuentas")
	public Object obtenerCuentas(){
		return apiBanregioService.obtenerCuentas();
	}
	
	@GetMapping("movimientos")
	public Movimientos obtenerMovimientos(){
		return apiBanregioService.obtenerMovimientos();
	}
	
}
