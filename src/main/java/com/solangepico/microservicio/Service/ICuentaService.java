package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Cuenta;
import com.solangepico.microservicio.Entity.Transaccion;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 
 * Clase que implementa los metodos de logica de la interface de CuentaService.
 */
public interface ICuentaService {
	Cuenta buscarCuenta(String identificacionPersona);

	List<Cuenta> listarCuenta();

	Cuenta crearCuenta(Cuenta nueva) throws Exception;

	Boolean editarCuenta(Cuenta editar, String identificacion) throws Exception;

	Boolean eliminarCuenta(int identificacionPersona) throws Exception;

	Cuenta buscarDatos(Transaccion datos);
}
