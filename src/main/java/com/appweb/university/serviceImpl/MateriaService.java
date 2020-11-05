package com.appweb.university.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appweb.university.entity.Materia;
import com.appweb.university.repository.MateriaRepository;
import com.appweb.university.service.IMateriaService;

@Service
public class MateriaService implements IMateriaService {

	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	public List<Materia> listarMaterias() {

		return (List<Materia>) materiaRepository.findAll();
	}

	@Override
	public void guardarMateria(Materia materia) {
		materiaRepository.save(materia);

	}

	@Override
	public Materia buscarPorIdMateria(Long id) {

		return materiaRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminarPorIdMateria(Long id) {
		materiaRepository.deleteById(id);

	}

}
