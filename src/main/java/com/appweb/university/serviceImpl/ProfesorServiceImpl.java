package com.appweb.university.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appweb.university.entity.Profesor;
import com.appweb.university.repository.ProfesorRepository;
import com.appweb.university.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private ProfesorRepository profesorRepository;

	@Override
	public List<Profesor> listarProfesores() {

		return (List<Profesor>) profesorRepository.findAll();
	}

	@Override
	public void guardarProfesor(Profesor profesor) {
		profesorRepository.save(profesor);

	}

	@Override
	public Profesor buscarPorIdProfesor(Long id) {

		return profesorRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminarProfesor(Long id) {
		profesorRepository.deleteById(id);

	}

}
