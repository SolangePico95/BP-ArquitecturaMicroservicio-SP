package com.solangepico.microservicio.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.solangepico.microservicio.Entity.Persona;
import com.solangepico.microservicio.Service.PersonaService;

/**
 * @author SolangePico
 * @version 1.0.0 14/01/2023 Controlador que manipula el flujo de los servicios
 *          rest del microservicio de personas.
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.DELETE })
@RequestMapping("api")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@PostMapping("/persona/crearPersona")
	public String crear(@RequestBody Persona personaNuevo) {
		try {
			personaService.crearPersona(personaNuevo);
		} catch (Exception e) {
			throw new RuntimeException("Error al insertar la persona: " + personaNuevo.getIdentificacion_persona());
		}
		return "Persona creada.";
	}

	@GetMapping("/persona/listarPersonas")
	public List<Persona> listaPersonas() {
		List<Persona> listaRetorno = new ArrayList<Persona>();
		try {
			listaRetorno = personaService.listarPersonas();
		} catch (Exception e) {
			throw new RuntimeException("No existe data");
		}
		return listaRetorno;
	}

	@PutMapping("/persona/editarPersona/{identificacion}")
	public String editar(@RequestBody Persona persona, @PathVariable("identificacion") String identificacion) {
		try {
			personaService.editarPersona(persona, identificacion);
		} catch (Exception e) {
			throw new RuntimeException("Error al editar la persona: " + persona.getId_persona());
		}
		return "Persona actualizado.";
	}

	@DeleteMapping("/persona/eliminarPersona/{id}")
	public String eliminar(@PathVariable("id") String id) {
		try {
			personaService.eliminarPersona(id);
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar la persona: " + id);
		}
		return "Persona eliminada.";
	}
}
