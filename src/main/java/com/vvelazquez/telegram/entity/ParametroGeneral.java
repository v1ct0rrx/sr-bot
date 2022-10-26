package com.vvelazquez.telegram.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="parametros_generales")
public class ParametroGeneral {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	@Column(name="token_refresh")
	private String tokenRefresh;
	
	@Column(name="fecha_ejecucion")
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechaEjecucion;
	
	@Column(name="envio_notificacion")
	private boolean envioNotificacion;
	
	@Column(name="imagen_notificacion")
	private String imagenNotificacion;
	
	private String mensaje;
	
	@Column(name="imagen_bienvenida")
	private String imagenBienvenida;
	
	@Column(name="mensaje_bienvenida")
	private String mensajeBienvenida;

	public ParametroGeneral() {
		super();
	}
	
	public ParametroGeneral(Long id, String token, String tokenRefresh) {
		super();
		this.id = id;
		this.token = token;
		this.tokenRefresh = tokenRefresh;
	}
	
}
