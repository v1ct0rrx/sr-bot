package com.vvelazquez.telegram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvelazquez.telegram.entity.NotificacionGeneral;
import com.vvelazquez.telegram.repository.NotificacionesGeneralesRepository;

@Service
public class NotificacionesGeneralesService {

	@Autowired
	private NotificacionesGeneralesRepository notificacionesGeneralesRepository;
	
	public List<NotificacionGeneral> obtenerActivos() {
		return notificacionesGeneralesRepository.findByActivoIsTrue();
	}

	public void desactivarNotificaciones(List<NotificacionGeneral> notificacionesGenerales) {
		notificacionesGeneralesRepository.desactivarNotificaciones(notificacionesGenerales);
	}
	
}
