package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.Cuenta;
import com.solangepico.microservicio.Entity.Transaccion;

public interface ICuentaService {
	Cuenta buscarCuenta(String identificacionPersona);

	List<Cuenta> listarCuenta();

	Cuenta crearCuenta(Cuenta nueva) throws Exception;

	Boolean editarCuenta(Cuenta editar, String identificacion) throws Exception;

	Boolean eliminarCuenta(int identificacionPersona) throws Exception;

	Cuenta buscarDatos(Transaccion datos);
}
