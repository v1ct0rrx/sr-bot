package com.vvelazquez.telegram.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuarios", indexes = { @Index(name = "chat_id_idx", columnList = "chat_id", unique = true) })
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="chat_id")
	private Long chatId;
	
	private boolean activo;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	public Usuario() {
		super();
	}

	public Usuario(Long chatId, boolean activo, Date fechaCreacion) {
		super();
		this.chatId = chatId;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
	}

	
}
