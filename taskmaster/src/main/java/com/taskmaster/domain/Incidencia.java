package com.taskmaster.domain;

import java.util.Objects;

public record Incidencia(
		String id,
		String titulo,
		String descripcion,
		String aula,
		String gravedad,
		boolean resuelta
		) {

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidencia other = (Incidencia) obj;
		return Objects.equals(id, other.id);
	}
	
	

}