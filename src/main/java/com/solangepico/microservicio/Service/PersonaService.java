package com.solangepico.microservicio.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solangepico.microservicio.Entity.Persona;
import com.solangepico.microservicio.Repository.PersonaRepository;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene los metodos de logica de negocio para persona.
 */
@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Persona buscarPersona(String identificacionPersona) {
		var persona = new Persona();
		persona = personaRepository.personaIdentificacion(identificacionPersona);
		return persona;
	}

	@Override
	public List<Persona> listarPersonas() {
		List<Persona> lista = new ArrayList<Persona>();
		lista = personaRepository.findAll();
		return lista;
	}

	@Override
	public Persona crearPersona(Persona nueva) throws Exception {
		Persona retorno = new Persona();
		try {
			var persona = new Persona();
			var PersonaRep = personaRepository.personaIdentificacion(nueva.getIdentificacion_persona());
			if (!Objects.nonNull(PersonaRep)) {
				persona = personaRepository.save(nueva);
				if (Objects.nonNull(persona)) {
					retorno = persona;
				}
			}
		} catch (Exception e) {
			throw new Exception("No se pudo crear el Registro!!");
		}

		return retorno;
	}

	@Override
	public Boolean editarPersona(Persona editar, String identificacion) throws Exception {
		boolean retorno = false;
		try {
			Persona depDB = personaRepository.personaIdentificacion(identificacion);
			if (Objects.nonNull(depDB)) {
				if (Objects.nonNull(editar.getNombre_persona()) && !"".equalsIgnoreCase(editar.getNombre_persona())) {
					depDB.setNombre_persona(editar.getNombre_persona());
				}
				if (Objects.nonNull(editar.getIdentificacion_persona())
						&& !"".equalsIgnoreCase(editar.getIdentificacion_persona())) {
					depDB.setIdentificacion_persona(editar.getIdentificacion_persona());
				}
				if (Objects.nonNull(editar.getEdad_persona()) && editar.getEdad_persona() > 0) {
					depDB.setEdad_persona(editar.getEdad_persona());
				}
				if (Objects.nonNull(editar.getDireccion_persona())
						&& !"".equalsIgnoreCase(editar.getDireccion_persona())) {
					depDB.setDireccion_persona(editar.getDireccion_persona());
				}
				if (Objects.nonNull(editar.getTelefono_persona())) {
					depDB.setTelefono_persona(editar.getTelefono_persona());
				}
				if (Objects.nonNull(editar.getGenero_persona()) && !"".equalsIgnoreCase(editar.getGenero_persona())) {
					depDB.setGenero_persona(editar.getGenero_persona());
				}
				personaRepository.save(depDB);
				retorno = true;
			} else {
				throw new Exception("Existe mas de un registro con ese numero de cedula");
			}
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el Registro!!");
		}
		return retorno;
	}

	@Override
	public Boolean eliminarPersona(String identificacionPersona) throws Exception {
		boolean retorno = false;
		try {
			Persona depDB = personaRepository.personaIdentificacion(identificacionPersona);
			if (Objects.nonNull(depDB)) {
				personaRepository.deleteById(depDB.getId_persona());
				retorno = true;
			} else {
				throw new Exception("Existe mas de un registro con ese numero de cedula");
			}
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el Registro!!");
		}
		return retorno;
	}
}
