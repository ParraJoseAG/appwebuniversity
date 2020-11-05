package com.appweb.university.service;

import java.util.List;

import com.appweb.university.entity.Profesor;

public interface IProfesorService {

	public List<Profesor> listarProfesores();

	public void guardarProfesor(Profesor profesor);

	public Profesor buscarPorIdProfesor(Long id);

	public void eliminarProfesor(Long id);

}
