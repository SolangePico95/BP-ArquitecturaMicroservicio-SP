package com.solangepico.microservicio.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.solangepico.microservicio.Entity.BusquedaHistorial;
import com.solangepico.microservicio.Entity.EstadoCuentaMovimiento;
import com.solangepico.microservicio.Entity.Transaccion;
import com.solangepico.microservicio.Service.MovimientoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.DELETE })
@RequestMapping("api")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;

	@PostMapping("/movimiento/crearMovimiento")
	public String crear(@RequestBody Transaccion nuevo) {
		try {
			var retorno = movimientoService.crearMovimiento(nuevo);
			if (retorno == 0) {
				throw new RuntimeException("Saldo no Dispoonible");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al insertar el movimiento");
		}
		return "Movimiento creado.";
	}

	@PostMapping("/movimiento/historialMovimiento")
	public List<EstadoCuentaMovimiento> historial(@RequestBody BusquedaHistorial nuevo) {
		List<EstadoCuentaMovimiento> listaRetorno = new ArrayList<EstadoCuentaMovimiento>();
		try {
			DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
			var fecha_Inicio = fechaHora.parse(nuevo.getFecha_Desde());
			var fecha_Fin = fechaHora.parse(nuevo.getFecha_Hasta());

			listaRetorno = movimientoService.buscarMovimientoFechas(nuevo.getCedula(), fecha_Inicio, fecha_Fin);
		} catch (Exception e) {
			throw new RuntimeException("No existen movimientos.");
		}
		return listaRetorno;
	}
}
