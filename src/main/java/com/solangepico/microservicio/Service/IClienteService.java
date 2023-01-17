package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Cliente;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 
 * Clase que implementa los metodos de logica de la interface de ClienteService.
 */
public interface IClienteService {
	List<Cliente> listarClientes();

	Cliente crearCliente(Cliente nueva) throws Exception;

	Boolean editarCliente(Cliente editar, String identificaion) throws Exception;

	Cliente buscarCliente(String identificacionPersona);

	Boolean eliminarCliente(String identificacionPersona) throws Exception;
}
