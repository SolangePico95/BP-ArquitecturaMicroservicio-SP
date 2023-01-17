package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Cliente;

public interface IClienteService {
	List<Cliente> listarClientes();

	Cliente crearCliente(Cliente nueva) throws Exception;

	Boolean editarCliente(Cliente editar, String identificaion) throws Exception;

	Cliente buscarCliente(String identificacionPersona);

	Boolean eliminarCliente(String identificacionPersona) throws Exception;
}
