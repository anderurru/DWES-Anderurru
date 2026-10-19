package com.taskmaster.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmaster.domain.Incidencia;

@Service
public class IncidenciaService {
	private final List<Incidencia> inventario;

	public IncidenciaService() {
		this.inventario = new ArrayList<>();

		inventario.add(new Incidencia("1", "Problema con la pizarra", "La pizarra no funciona correctamente",
				"Aula 101", "ALTA", false));
		inventario.add(new Incidencia("2", "Problema con el proyector", "El proyector no enciende", "Aula 102",
				"MODERADA", true));
		inventario.add(new Incidencia("3", "Problema con el aire acondicionado", "El aire acondicionado no enfría",
				"Aula 103", "CRÍTICA", false));
		inventario.add(new Incidencia("4", "Problema con la luz", "La luz parpadea constantemente", "Aula 104", "ALTA",
				false));

	}

	public List<Incidencia> obtenerTodas() {
		return new ArrayList<>(this.inventario);
	}
	
	public List<Incidencia> filtrarPorGravedad(String gravedad) {
		return inventario.stream().filter(i -> i.gravedad().equalsIgnoreCase(gravedad)).toList();
	}

	public Incidencia buscarPorId(String id) {
		return inventario.stream().filter(i -> i.id().equals(id)).findFirst().orElse(null);
	}

	public boolean guardar(Incidencia incidencia) {
		if (incidencia.titulo() == null || incidencia.titulo().isBlank()) {
			return false;
		}
		if (inventario.stream().anyMatch(i -> i.id().equalsIgnoreCase(incidencia.id()))) {
			return false;
		}
		if (incidencia.gravedad() == null || incidencia.gravedad().isBlank()) {
			return false;
		}
		inventario.add(incidencia);
		return true;
	}

}
