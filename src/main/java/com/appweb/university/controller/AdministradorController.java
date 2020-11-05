package com.appweb.university.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appweb.university.entity.Materia;
import com.appweb.university.entity.Profesor;
import com.appweb.university.service.IMateriaService;
import com.appweb.university.service.IProfesorService;

@Controller
public class AdministradorController {
	@Autowired
	private IProfesorService profesorService;

	@Autowired
	private IMateriaService materiaService;

	@GetMapping("/admin/listaProfesores")
	public String listarProfesores(Model modelo) {

		List<Profesor> listaProfesores = new ArrayList<Profesor>();
		listaProfesores.addAll(profesorService.listarProfesores());

		modelo.addAttribute("listaProfesores", listaProfesores);

		return "views/administradores/listaProfesores";

	}

	@GetMapping("/admin/registrar")
	public String registrarProfesor(Model modelo) {

		Profesor profesor = new Profesor();
		modelo.addAttribute("profesor", profesor);
		modelo.addAttribute("titulo", "REGISTRAR NUEVO PROFESOR EN LA BBDD: ");
		modelo.addAttribute("accion", "registrar");

		return "views/administradores/formularioRegistroProfesor";

	}

	@PostMapping("/admin/guardar")
	public String guardarProfesor(@ModelAttribute Profesor profesor, RedirectAttributes attribute) {

		profesorService.guardarProfesor(profesor);
		attribute.addFlashAttribute("success", "Profesor registrado con éxito!");
		return "redirect:/admin/listaProfesores";
	}

	@GetMapping("/admin/editar/{id}")
	public String editar(@PathVariable("id") Long idProfesor, Model modelo, RedirectAttributes attribute) {

		Profesor profesor = null;
		if (idProfesor > 0) {
			profesor = profesorService.buscarPorIdProfesor(idProfesor);

			if (profesor == null) {

				attribute.addFlashAttribute("error", "ATENCIÓN: el ID del Profesor no existe!");
				return "redirect:/admin/listaProfesores";
			}
		} else {
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el ID del Profesor!");
			return "redirect:/admin/listaProfesores";
		}

		modelo.addAttribute("titulo", "EDITANDO AL PROFESOR: " + profesor.getNombre() + " " + profesor.getApellido());
		modelo.addAttribute("profesor", profesor);
		return "views/administradores/formularioRegistroProfesor";
	}

	@GetMapping("/admin/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long idProfesor, Model modelo, RedirectAttributes attribute) {

		Profesor profesor = null;
		if (idProfesor > 0) {
			profesor = profesorService.buscarPorIdProfesor(idProfesor);

			if (profesor == null) {

				attribute.addFlashAttribute("error", "ATENCIÓN: el ID del Profesor no existe!");
				return "redirect:/admin/listaProfesores";
			}
		} else {
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el ID del Profesor!");
			return "redirect:/admin/listaProfesores";
		}

		profesorService.eliminarProfesor(idProfesor);
		attribute.addFlashAttribute("warning", "Profesor eliminado con éxito!");
		return "redirect:/admin/listaProfesores";
	}

	// ...............................................................................................

	@GetMapping("/admin/listaMaterias")
	public String listarMaterias(Model modelo) {

		List<Materia> listaMaterias = new ArrayList<Materia>();
		listaMaterias.addAll(materiaService.listarMaterias());

		modelo.addAttribute("listaMaterias", listaMaterias);
		return "views/administradores/listaMaterias";

	}

	@GetMapping("/admin/registrarMateria")
	public String registrarMateriar(Model modelo) {

		Materia materia = new Materia();

		List<Profesor> listaProfesores = new ArrayList<Profesor>();
		List<Profesor> listaProfesoresActivos = new ArrayList<Profesor>();
		listaProfesores.addAll(profesorService.listarProfesores());

		for (Profesor profesor : listaProfesores) {
			if (profesor.isActivo()) {
				listaProfesoresActivos.add(profesor);
			}
		}

		modelo.addAttribute("titulo", "REGISTRAR UN NUEVO CURSO EN LA BBDD:");
		modelo.addAttribute("materia", materia);
		modelo.addAttribute("profesores", listaProfesoresActivos);
		modelo.addAttribute("accion", "registrar");

		return "views/administradores/formularioRegistroMaterias";

	}

	@PostMapping("/admin/guardarMateria")
	public String guardarMateria(@ModelAttribute Materia materia, RedirectAttributes attribute) {

		materiaService.guardarMateria(materia);
		attribute.addFlashAttribute("success", "Materia registrada con éxito!");
		return "redirect:/admin/listaMaterias";
	}

	@GetMapping("/admin/editarMateria/{id}")
	public String editarMateria(@PathVariable("id") Long idMateria, Model modelo, RedirectAttributes attribute) {
		List<Profesor> listaProfesores = new ArrayList<Profesor>();
		List<Profesor> listaProfesoresActivos = new ArrayList<Profesor>();
		listaProfesores.addAll(profesorService.listarProfesores());

		for (Profesor profesor : listaProfesores) {
			if (profesor.isActivo()) {
				listaProfesoresActivos.add(profesor);
			}
		}

		Materia materia = null;
		if (idMateria > 0) {
			materia = materiaService.buscarPorIdMateria(idMateria);

			if (materia == null) {

				attribute.addFlashAttribute("error", "ATENCIÓN: el ID de la materia no existe!");
				return "redirect:/admin/listaMaterias";
			}
		} else {
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el ID del Materia!");
			return "redirect:/admin/listaMaterias";
		}

		modelo.addAttribute("titulo", "EDITANDO LA MATERIA: " + materia.getNombreMateria());
		modelo.addAttribute("materia", materia);
		modelo.addAttribute("profesores", listaProfesoresActivos);
		return "views/administradores/formularioRegistroMaterias";
	}

	@GetMapping("/admin/eliminarMateria/{id}")
	public String eliminarMateria(@PathVariable("id") Long idMateria, Model modelo, RedirectAttributes attribute) {

		Materia materia = null;
		if (idMateria > 0) {
			materia = materiaService.buscarPorIdMateria(idMateria);

			if (materia == null) {

				attribute.addFlashAttribute("error", "ATENCIÓN: el ID de la materia no existe!");
				return "redirect:/admin/listaMaterias";
			}
		} else {
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el ID de la materia!");
			return "redirect:/admin/listaMaterias";
		}

		materiaService.eliminarPorIdMateria(idMateria);
		;
		attribute.addFlashAttribute("warning", "Materia eliminada con éxito!");
		return "redirect:/admin/listaMaterias";
	}

}
