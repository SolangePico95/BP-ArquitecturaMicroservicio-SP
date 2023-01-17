package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Estado;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 
 * Clase que implementa los metodos de logica de la interface de EstadoService.
 */
public interface IEstadoService {
	Estado buscarEstado(String descripcion);

	List<Estado> listarEstados();

	Estado crearEstado(Estado nueva) throws Exception;

	Boolean editarEstado(Estado editar) throws Exception;

	Boolean eliminarEstado(String descripcion) throws Exception;
}
