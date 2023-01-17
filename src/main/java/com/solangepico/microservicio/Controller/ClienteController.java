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

import com.solangepico.microservicio.Entity.Cliente;
import com.solangepico.microservicio.Service.ClienteService;

/**
 * @author SolangePico
 * @version 1.0.0 14/01/2023 Controlador que manipula el flujo de los servicios
 *          rest del microservicio de clientes.
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.DELETE })
@RequestMapping("api")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/cliente/crearCliente")
	public String crear(@RequestBody Cliente clienteNuevo) {
		try {
			clienteService.crearCliente(clienteNuevo);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error al insertar el cliente: " + clienteNuevo.getPersona().getIdentificacion_persona());
		}
		return "Cliente creado.";
	}

	@GetMapping("/cliente/listarClientes")
	public List<Cliente> listaClientes() {
		List<Cliente> listaRetorno = new ArrayList<Cliente>();
		try {
			listaRetorno = clienteService.listarClientes();
		} catch (Exception e) {
			throw new RuntimeException("No existem clientes.");
		}
		return listaRetorno;
	}

	@PutMapping("/cliente/editarCliente/{id}")
	public String editar(@RequestBody Cliente clienteEdit, @PathVariable("id") String id) {
		try {
			clienteService.editarCliente(clienteEdit, id);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error al editar el cliente: " + clienteEdit.getPersona().getIdentificacion_persona());
		}
		return "Cliente actualizado.";
	}

	@DeleteMapping("/cliente/eliminarCliente/{id}")
	public String eliminar(@PathVariable("id") String id) {
		try {
			clienteService.eliminarCliente(id);
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar el cliente" + id);
		}
		return "Cliente eliminado.";
	}
}
