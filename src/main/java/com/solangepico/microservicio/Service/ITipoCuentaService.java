package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.TipoCuenta;

/**
 * @author SolangePico
 * @version 1.0.0 14/01/2023 
 * Clase que implementa los metodos de logica de la interface de TipocuentaService.
 */
public interface ITipoCuentaService {
	List<TipoCuenta> listarTipoCuenta();

	TipoCuenta crearTipoCuenta(TipoCuenta nueva) throws Exception;

	Boolean editarTipoCuenta(TipoCuenta editar) throws Exception;

	TipoCuenta buscarTipoCuenta(String descripcion);

	Boolean eliminarTipoCuenta(String identificacionPersona) throws Exception;
}
