package com.vvelazquez.telegram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvelazquez.telegram.entity.ParametroGeneral;
import com.vvelazquez.telegram.repository.ParametrosGeneralesRepository;

@Service
public class ParametrosGeneralesService {

	@Autowired
	private ParametrosGeneralesRepository parametrosGeneralesRepository;
	
	public void saveOrUpdate(ParametroGeneral parametroGeneral) {
		parametrosGeneralesRepository.save(parametroGeneral);
	}
	
	public Optional<ParametroGeneral> obtener(Long id) {
		return parametrosGeneralesRepository.findById(id);
	}
	
	public List<ParametroGeneral> obtener() {
		return parametrosGeneralesRepository.findAll();
	}

	public void actualizarToken(ParametroGeneral parametroGeneral) {
		parametrosGeneralesRepository.actualizarToken(parametroGeneral);
		
	}
}
