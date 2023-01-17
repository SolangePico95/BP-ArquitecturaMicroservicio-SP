package com.solangepico.microservicio.Service;

import java.util.List;

import com.solangepico.microservicio.Entity.TipoCuenta;

public interface ITipoCuentaService {
	List<TipoCuenta> listarTipoCuenta();

	TipoCuenta crearTipoCuenta(TipoCuenta nueva) throws Exception;

	Boolean editarTipoCuenta(TipoCuenta editar) throws Exception;

	TipoCuenta buscarTipoCuenta(String descripcion);

	Boolean eliminarTipoCuenta(String identificacionPersona) throws Exception;
}
