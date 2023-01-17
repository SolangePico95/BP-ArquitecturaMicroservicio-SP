package com.solangepico.microservicio.Service;

import java.util.Date;
import java.util.List;

import com.solangepico.microservicio.Entity.EstadoCuentaMovimiento;
import com.solangepico.microservicio.Entity.Transaccion;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 
 * Clase que implementa los metodos de logica de la interface de MovimientoService.
 */
public interface IMovimientoService {
	int crearMovimiento(Transaccion nuevo) throws Exception;

	List<EstadoCuentaMovimiento> buscarMovimientoFechas(Long identificacionPersona, Date FechaDesde, Date FechaHasta);
}
