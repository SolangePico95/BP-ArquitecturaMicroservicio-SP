package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Persona;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 
 * Clase que implementa los metodos de logica de la interface de PersonaService.
 */
public interface IPersonaService {

	Persona buscarPersona(String identificacionPersona);

	List<Persona> listarPersonas();

	Persona crearPersona(Persona nueva) throws Exception;

	Boolean editarPersona(Persona editar, String identificacion) throws Exception;

	Boolean eliminarPersona(String identificacionPersona) throws Exception;
}
