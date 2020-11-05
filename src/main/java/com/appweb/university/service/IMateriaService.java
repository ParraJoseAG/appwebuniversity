package com.appweb.university.service;

import java.util.List;

import com.appweb.university.entity.Materia;

public interface IMateriaService {
	public List<Materia> listarMaterias();

	public void guardarMateria(Materia materia);

	public Materia buscarPorIdMateria(Long id);

	public void eliminarPorIdMateria(Long id);
}
