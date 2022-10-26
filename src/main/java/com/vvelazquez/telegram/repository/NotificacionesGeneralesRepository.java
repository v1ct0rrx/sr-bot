package com.vvelazquez.telegram.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vvelazquez.telegram.entity.NotificacionGeneral;

@Repository
public interface NotificacionesGeneralesRepository extends JpaRepository<NotificacionGeneral, Long> {

	List<NotificacionGeneral> findByActivoIsTrue();

	@Modifying
	@Transactional
	@Query("UPDATE NotificacionGeneral notifica SET notifica.activo = FALSE where notifica IN :notificaciones")
	void desactivarNotificaciones(@Param("notificaciones") List<NotificacionGeneral> notificacionesGenerales);

}
