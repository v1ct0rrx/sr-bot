package com.vvelazquez.telegram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvelazquez.telegram.entity.Usuario;
import com.vvelazquez.telegram.repository.UsuariosRepository;

@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	public List<Usuario> obtener(){
		return usuariosRepository.findAll();
	}

	public List<Usuario> obtenerActivos() {
		return usuariosRepository.findByActivoIsTrue();
	}

	public boolean obtenerPorChatId(Long chatId) {
		return usuariosRepository.obtenerPorChatId(chatId);
	}
	
	public void saveOrUpdate(Usuario usuario) {
		usuariosRepository.save(usuario);
	}
}
