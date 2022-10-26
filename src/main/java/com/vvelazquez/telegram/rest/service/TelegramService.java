package com.vvelazquez.telegram.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.vvelazquez.telegram.entity.Usuario;
import com.vvelazquez.telegram.utils.Constantes;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TelegramService {

	@Value("${telegram.token}")
	private String telegramToken;

	@Value("${telegram.users}")
	private String[] telegramUsuarios;
	
	public void enviarMensaje(Long chatId, String mensaje) {
		TelegramBot bot = new TelegramBot(telegramToken);

		log.info("Enviando mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
		bot.execute(new SendMessage(chatId, mensaje));
		log.info("Finaliza el envio mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
	}

	public void enviarMensaje(Long chatId, String mensaje, final byte[] array) {
		TelegramBot bot = new TelegramBot(telegramToken);

		log.info("Enviando mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
		SendPhoto sendPhoto = new SendPhoto(chatId, array);
		sendPhoto.caption(mensaje);
		bot.execute(sendPhoto);
		log.info("Finaliza el envio mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
	}
	
	public void enviarMensaje(final byte[] array, String mensaje) {
		TelegramBot bot = new TelegramBot(telegramToken);

		log.info("Enviando mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
		Arrays.asList(telegramUsuarios).forEach(user -> {
			SendPhoto sendPhoto = new SendPhoto(user, array);
			sendPhoto.caption(mensaje);
			bot.execute(sendPhoto);
		});
		log.info("Finaliza el envio mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
	}
	
	public void enviarMensaje(final byte[] array, String mensaje, List<Usuario> usuarios) {
		TelegramBot bot = new TelegramBot(telegramToken);

		log.info("Enviando mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
		usuarios.parallelStream().forEach(user -> {
			SendPhoto sendPhoto = new SendPhoto(user.getChatId(), array);
			sendPhoto.caption(mensaje);
			bot.execute(sendPhoto);
		});
			
		log.info("Finaliza el envio mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
	}
	
	public List<Usuario> mensajesRecibidos() {
		TelegramBot bot = new TelegramBot(telegramToken);

		GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
		GetUpdatesResponse getUpdatesResponse = bot.execute(getUpdates);

		List<Update> updates = getUpdatesResponse.updates();
		
		List<Usuario> usuarios = new ArrayList<>();
		
		updates.forEach(update ->{
			if(update.message() != null) {
				try {
					usuarios.add(new Usuario(update.message().chat().id(), false, new Date()));	
				}catch(Exception ex) {
					log.error("Error en la alta del update " + update , ex);
				}
				
			}
		});
			
		log.info("Finaliza el envio mensajes {} ", Constantes.DATE_FORMAT.format(new Date()));
		return usuarios;
	}
	
}
