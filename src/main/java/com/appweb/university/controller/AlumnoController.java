package com.appweb.university.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appweb.university.entity.Materia;
import com.appweb.university.service.IMateriaService;

@Controller
public class AlumnoController {

	private List<Materia> materiaInscriptas = new ArrayList<Materia>();

	@Autowired
	private IMateriaService materiaService;

	@GetMapping("/alumno/inscribirMaterias")
	public String inscribirMaterias(Model modelo) {

		List<Materia> listaMaterias = new ArrayList<Materia>();
		listaMaterias.addAll(materiaService.listarMaterias());
		listaMaterias.removeAll(materiaInscriptas);
		modelo.addAttribute("listaMaterias", listaMaterias);

		return "views/alumnos/materiasDisponibles";
	}

	@GetMapping("/alumno/inscribir/{id}")
	public String inscribir(@PathVariable("id") Long idMateria, Model modelo, RedirectAttributes attribute) {

		List<Materia> listaMaterias = new ArrayList<Materia>();
		listaMaterias.addAll(materiaService.listarMaterias());
		Materia materia = materiaService.buscarPorIdMateria(idMateria);

		int cuposDisponibles = materia.getCupoMaximoAlumno();

		if (cuposDisponibles > 0) {
			attribute.addFlashAttribute("success", "Materia registrada con éxito!");
			materiaInscriptas.add(materia);
			materia.setCupoMaximoAlumno(cuposDisponibles - 1);
			materiaService.guardarMateria(materia);
			listaMaterias.removeAll(materiaInscriptas);
			modelo.addAttribute("listaMaterias", listaMaterias);

			return "views/alumnos/materiasDisponibles";
		} else {
			attribute.addFlashAttribute("error", "ATENCIÓN: Ya no se encuentran cupos disponibles para esta Materia!");
			return "views/alumnos/materiasDisponibles";
		}

	}
	
	@GetMapping("/alumno/misMaterias")
	public String materiasInscriptas(Model modelo) {

		modelo.addAttribute("materiaInscriptas", materiaInscriptas);

		return "views/alumnos/materiasInscriptas";
	}
}
