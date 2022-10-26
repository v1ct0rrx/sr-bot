package com.vvelazquez.telegram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vvelazquez.telegram.entity.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByActivoIsTrue();

	@Query("SELECT COUNT(id) > 0 FROM Usuario usuario WHERE usuario.chatId = :chatId")
	boolean obtenerPorChatId(@Param("chatId") Long chatId);

}
