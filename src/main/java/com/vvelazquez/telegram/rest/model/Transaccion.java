package com.vvelazquez.telegram.rest.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaccion {
	
	@JsonProperty("transaction_number")
	private String numeroTransaccion;
	
	@JsonProperty("reference_number")
	private String referencia;
	
	@JsonProperty("description")
	private String descripcion;
	
	@JsonProperty("amount")
	private BigDecimal monto;
	
	@JsonProperty("type")
	private String naturaleza;

}
