package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Estado;

public interface IEstadoService {
	Estado buscarEstado(String descripcion);

	List<Estado> listarEstados();

	Estado crearEstado(Estado nueva) throws Exception;

	Boolean editarEstado(Estado editar) throws Exception;

	Boolean eliminarEstado(String descripcion) throws Exception;
}
