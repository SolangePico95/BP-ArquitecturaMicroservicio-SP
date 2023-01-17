package com.solangepico.microservicio.Service;

import java.util.Date;
import java.util.List;

import com.solangepico.microservicio.Entity.EstadoCuentaMovimiento;
import com.solangepico.microservicio.Entity.Transaccion;

public interface IMovimientoService {
	int crearMovimiento(Transaccion nuevo) throws Exception;

	List<EstadoCuentaMovimiento> buscarMovimientoFechas(Long identificacionPersona, Date FechaDesde, Date FechaHasta);
}
