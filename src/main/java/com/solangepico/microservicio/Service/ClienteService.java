package com.solangepico.microservicio.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solangepico.microservicio.Entity.Cliente;
import com.solangepico.microservicio.Entity.Persona;
import com.solangepico.microservicio.Repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PersonaService personaService;

	@Override
	public Cliente buscarCliente(String identificacionPersona) {
		var cliente = new Cliente();
		cliente = clienteRepository.clienteIdPersona(identificacionPersona);
		return cliente;
	}

	@Override
	public List<Cliente> listarClientes() {
		List<Cliente> lista = new ArrayList<Cliente>();
		lista = clienteRepository.findAll();
		return lista;
	}

	@Override
	public Cliente crearCliente(Cliente nueva) throws Exception {
		Cliente retorno = new Cliente();
		try {
			var cliente = new Cliente();
			var persona = nueva.getPersona();
			
			var PersonaRep = personaService.buscarPersona(persona.getIdentificacion_persona());
			if (!Objects.nonNull(PersonaRep)) {
				var nuevaPersona = personaService.crearPersona(persona);
				nueva.setId_persona(nuevaPersona.getId_persona());
				cliente = clienteRepository.save(nueva);
				if (Objects.nonNull(cliente)) {
					retorno = cliente;
				}
			} else {
				nueva.setId_persona(PersonaRep.getId_persona());
				cliente = clienteRepository.save(nueva);
				if (Objects.nonNull(cliente)) {
					retorno = cliente;
				}
			}

		} catch (Exception e) {
			throw new Exception("No se pudo crear el Registro!!");
		}
		return retorno;
	}

	@Override
	public Boolean editarCliente(Cliente editar, String identificaion) throws Exception {
		boolean retorno = false;
		try {

			Cliente depDB = clienteRepository.clienteIdPersona(identificaion);
			if (Objects.nonNull(depDB)) {
				var persona = new Persona();
				persona.setId_persona(depDB.getId_persona());
				persona.setNombre_persona(editar.getPersona().getNombre_persona());
				persona.setEdad_persona(editar.getPersona().getEdad_persona());
				persona.setIdentificacion_persona(editar.getPersona().getIdentificacion_persona());
				persona.setDireccion_persona(editar.getPersona().getDireccion_persona());
				persona.setTelefono_persona(editar.getPersona().getTelefono_persona());
				persona.setGenero_persona(editar.getPersona().getGenero_persona());
				personaService.editarPersona(persona, identificaion);
				if (Objects.nonNull(editar.getContrasena_cliente())
						&& !"".equalsIgnoreCase(editar.getContrasena_cliente())) {
					depDB.setContrasena_cliente(editar.getContrasena_cliente());
				}
				if (Objects.nonNull(editar.getEstado_cliente())) {
					depDB.setEstado_cliente(editar.getEstado_cliente());
				}
				clienteRepository.save(depDB);
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
	public Boolean eliminarCliente(String identificacionPersona) throws Exception {
		boolean retorno = false;
		try {
			Cliente depDB = clienteRepository.clienteIdPersona(identificacionPersona);
			if (Objects.nonNull(depDB)) {				
				depDB.setEstado_cliente(2);
				clienteRepository.save(depDB);
				retorno = true;
			} else {
				throw new Exception("Existe mas de un registro con ese numero de cedula");
			}
		} catch (Exception e) {
			throw new Exception("No se pudo elminar el Registro!!");
		}
		return retorno;
	}

}
