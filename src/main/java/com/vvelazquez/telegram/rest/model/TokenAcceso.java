package com.vvelazquez.telegram.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenAcceso {
	
	@JsonProperty(value = "grant_type", index = 1)
	private String grantType;
	
	@JsonProperty(value = "client_id", index = 2)
	private String clientId;
	
	@JsonProperty(value = "client_secret", index = 3)
	private String clienteSecret;
	
	@JsonProperty(value = "refresh_token", index = 4)
	private String refreshToken;
	
	public TokenAcceso() {
		super();
	}

	public TokenAcceso(String grantType, String clientId, String clienteSecret, String refreshToken) {
		super();
		this.grantType = grantType;
		this.clientId = clientId;
		this.clienteSecret = clienteSecret;
		this.refreshToken = refreshToken;
	}

}
