package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Persona;

public interface IPersonaService {

	Persona buscarPersona(String identificacionPersona);

	List<Persona> listarPersonas();

	Persona crearPersona(Persona nueva) throws Exception;

	Boolean editarPersona(Persona editar, String identificacion) throws Exception;

	Boolean eliminarPersona(String identificacionPersona) throws Exception;
}
