package com.vvelazquez.telegram.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vvelazquez.telegram.entity.ParametroGeneral;

@Repository
public interface ParametrosGeneralesRepository extends JpaRepository<ParametroGeneral, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE ParametroGeneral set token = :#{#parametro.token}, tokenRefresh = :#{#parametro.tokenRefresh}")
	void actualizarToken(@Param("parametro") ParametroGeneral parametroGeneral);

}
