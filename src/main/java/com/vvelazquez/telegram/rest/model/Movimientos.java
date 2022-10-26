package com.vvelazquez.telegram.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movimientos {
	
	@JsonProperty("transactions")
	private List<Transaccion> transacciones;

}
