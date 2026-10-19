package com.taskmaster.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.taskmaster.domain.Incidencia;

class IncidenciaServiceTest {

    private final IncidenciaService service = new IncidenciaService();

    @Test
    void filtrarPorGravedadAlta_devuelveSoloIncidenciasAlta() {
        List<Incidencia> incidencias = service.filtrarPorGravedad("alta");

        assertFalse(incidencias.isEmpty());	
        assertTrue(incidencias.stream()
                .allMatch(incidencia -> incidencia.gravedad().equalsIgnoreCase("ALTA")));
    }

    @Test
    void filtrarPorGravedadInexistente_devuelveListaVacia() {
        List<Incidencia> incidencias = service.filtrarPorGravedad("INEXISTENTE");

        assertTrue(incidencias.isEmpty());
    }
}
