package com.vvelazquez.telegram.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenModel {
	
	@JsonProperty(value = "access_token")
	private String token;

	@JsonProperty(value = "token_type")
	private String tipoToken;
	
	@JsonProperty(value = "expires_in")
	private short tiempoExpiracion;
	
	@JsonProperty(value = "refresh_token")
	private String tokenRefresco;
	
	@JsonProperty(value = "scope")
	private String scope;

}
