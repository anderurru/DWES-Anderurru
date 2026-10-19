package com.taskmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ IMPORT CORRECTO
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskmaster.domain.Incidencia;
import com.taskmaster.service.IncidenciaService;

@Controller
public class IncidenciaController {
	private final IncidenciaService incidenciaService;

	public IncidenciaController(@Autowired IncidenciaService service) {
		this.incidenciaService = service;
	}

	@GetMapping("/incidencias")
	public String getIncidencias(@RequestParam(name = "gravedad", required = false) String gravedad, Model model) {
		if (gravedad == null || gravedad.isBlank()) {
			model.addAttribute("listadoIncidencias", incidenciaService.obtenerTodas());
		} else {
			model.addAttribute("listadoIncidencias", incidenciaService.filtrarPorGravedad(gravedad));
		}
		model.addAttribute("gravedadFiltro", gravedad);
		return "incidencias";
	}

	@GetMapping("/incidencias/id/{idIncidencia}")
	public ModelAndView getIncidencia(@PathVariable("idIncidencia") String id) {
		Incidencia incidencia = incidenciaService.buscarPorId(id);

		if (incidencia == null) {
			return new ModelAndView("redirect:/incidencias");
		}

		ModelAndView mv = new ModelAndView("detalle_incidencia");
		mv.addObject("IncidenciaDetallada", incidencia);
		return mv;
	}

	@GetMapping("/incidencias/nueva")
	public String crearFormulario() {
		return "formulario_incidencia";

	}

	@PostMapping("/incidencias/guardar")
	public String crearIncidencia(@RequestParam() String id, @RequestParam(required = true) String titulo,
			@RequestParam String descripcion, @RequestParam String aula, @RequestParam String gravedad,
			RedirectAttributes atts) {
		Incidencia in = new Incidencia(id, titulo, descripcion, aula, gravedad.toUpperCase(), false);
		boolean guardada = incidenciaService.guardar(in);
		if(!guardada) {
			atts.addFlashAttribute("error", "Error: La incidencia tiene el id duplicado o el titulo vacio.");
			return "redirect:/incidencias/nueva";
		}
		atts.addFlashAttribute("mensaje", "Incidencia creada con exito");
		return "redirect:/incidencias";

	}
}
