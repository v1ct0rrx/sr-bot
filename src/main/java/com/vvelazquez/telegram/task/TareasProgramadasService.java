package com.vvelazquez.telegram.task;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vvelazquez.telegram.entity.NotificacionGeneral;
import com.vvelazquez.telegram.entity.ParametroGeneral;
import com.vvelazquez.telegram.entity.Usuario;
import com.vvelazquez.telegram.rest.model.CuentasModel;
import com.vvelazquez.telegram.rest.model.Movimientos;
import com.vvelazquez.telegram.rest.model.Transaccion;
import com.vvelazquez.telegram.rest.service.ApiBanregioService;
import com.vvelazquez.telegram.rest.service.TelegramService;
import com.vvelazquez.telegram.service.NotificacionesGeneralesService;
import com.vvelazquez.telegram.service.ParametrosGeneralesService;
import com.vvelazquez.telegram.service.UsuariosService;
import com.vvelazquez.telegram.utils.Constantes;
import com.vvelazquez.telegram.utils.Utilidades;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class TareasProgramadasService {

	@Autowired
	private ApiBanregioService apiBanregioService;

	@Autowired
	private TelegramService telegramService;

	@Autowired
	private ParametrosGeneralesService parametrosGeneralesService;

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private Utilidades utilidades;

	@Autowired
	private NotificacionesGeneralesService notificacionesGeneralesService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void notificarNomina() {
		log.info("Arrancamos notificacion a las {}", Constantes.DATE_FORMAT.format(new Date()));

		ParametroGeneral parametroGeneral = parametrosGeneralesService.obtener(1L).get();

		Movimientos movimientos = null;
		if (parametroGeneral.isEnvioNotificacion()) {
			movimientos = apiBanregioService.obtenerMovimientos();
		} else {
			CuentasModel cuentasModel = apiBanregioService.obtenerCuentas();
			log.info("Se obtiene cuenta con id {}", cuentasModel.getCuentasModel().get(0).getId());
		}

		if (movimientos != null && movimientos.getTransacciones() != null
				&& !movimientos.getTransacciones().isEmpty()) {

			List<Transaccion> transacciones = movimientos.getTransacciones();
			
			log.info(transacciones);

			Long pagosConReferencia = transacciones.stream().filter(tr -> Constantes.ABONO.equals(tr.getNaturaleza()))
					.filter(tr -> Constantes.REFERENCIA.equals(tr.getReferencia())).count();

			if (pagosConReferencia != null && pagosConReferencia > 0) {

				parametroGeneral.setEnvioNotificacion(false);
				parametrosGeneralesService.saveOrUpdate(parametroGeneral);
				List<Usuario> usuarios = usuariosService.obtenerActivos();
				try {
					byte[] data = utilidades.obtenerImagen(parametroGeneral.getImagenNotificacion());
					telegramService.enviarMensaje(data, parametroGeneral.getMensaje(), usuarios);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	@Scheduled(cron = "0 */1 * * * ?")
	public void registrarUsuarios() {
		log.info("Registro de usuarios a las {}", Constantes.DATE_FORMAT.format(new Date()));

		List<Usuario> usuarios = telegramService.mensajesRecibidos();
		ParametroGeneral parametroGeneral = parametrosGeneralesService.obtener(1L).get();
		usuarios.forEach(user -> {
			boolean existe = usuariosService.obtenerPorChatId(user.getChatId());
			if (!existe) {
				usuariosService.saveOrUpdate(user);
				log.info("Usuario {} registrado correctamente", user.getChatId());
				try {
					byte[] data = utilidades.obtenerImagen(parametroGeneral.getImagenBienvenida());
					telegramService.enviarMensaje(user.getChatId(), parametroGeneral.getMensajeBienvenida(), data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Scheduled(cron = "0 */5 * * * ?")
	public void notificacionesGenerales() {
		log.info("Notificaciones generales a las {}", Constantes.DATE_FORMAT.format(new Date()));

		List<NotificacionGeneral> notificacionesGenerales = notificacionesGeneralesService.obtenerActivos();
		List<Usuario> usuarios = usuariosService.obtenerActivos();

		notificacionesGeneralesService.desactivarNotificaciones(notificacionesGenerales);

		notificacionesGenerales.forEach(notifica -> {
			try {
				byte[] data = utilidades.obtenerImagen(notifica.getImagen());
				telegramService.enviarMensaje(data, notifica.getMensaje(), usuarios);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		log.info("Finaliza notificaciones generales a las {}", Constantes.DATE_FORMAT.format(new Date()));
	}

}
