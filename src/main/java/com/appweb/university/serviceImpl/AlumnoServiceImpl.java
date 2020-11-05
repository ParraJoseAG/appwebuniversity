package com.appweb.university.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appweb.university.entity.Alumno;
import com.appweb.university.repository.AlumnoRepository;
import com.appweb.university.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Override
	public Alumno buscarPorIdAlumno(String id) {
		
		return alumnoRepository.findById(id).orElse(null);
	}

}
