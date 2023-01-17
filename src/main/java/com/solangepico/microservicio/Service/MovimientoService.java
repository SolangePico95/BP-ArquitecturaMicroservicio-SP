package com.solangepico.microservicio.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solangepico.microservicio.Entity.Cuenta;
import com.solangepico.microservicio.Entity.Movimiento;
import com.solangepico.microservicio.Entity.EstadoCuentaMovimiento;
import com.solangepico.microservicio.Entity.Transaccion;
import com.solangepico.microservicio.Repository.MovimientoRepository;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene los metodos de logica de negocio para movimiento.
 */
@Service
public class MovimientoService implements IMovimientoService {

	@Autowired
	private CuentaService cuentaService;
	@Autowired
	private MovimientoRepository movimientoRepository;

	@Override
	public List<EstadoCuentaMovimiento> buscarMovimientoFechas(Long identificacionPersona, Date FechaDesde,
			Date FechaHasta) {
		List<EstadoCuentaMovimiento> retorno = new ArrayList<EstadoCuentaMovimiento>();
		List<Movimiento> lista = new ArrayList<Movimiento>();
		lista = movimientoRepository.historialMovimientosxCliente(identificacionPersona);
		if (Objects.nonNull(lista)) {
			lista.forEach(x -> {
				if (x.getFecha_movimiento().compareTo(FechaDesde) >= 0
						&& FechaHasta.compareTo(x.getFecha_movimiento()) >= 0) {
					EstadoCuentaMovimiento item = new EstadoCuentaMovimiento();
					item.setCliente(x.getCuenta().getCliente().getPersona().getNombre_persona());
					item.setFecha(x.getFecha_movimiento());
					item.setNumero_Cuenta(x.getCuenta().getNumero_cuenta());
					item.setTipo(x.getCuenta().getTipo_cuenta());
					item.setSaldo_inicial(x.getCuenta().getSaldo_inicial_cuenta());
					item.setEstado(x.getEstado_movimiento());
					item.setMovimiento(x.getDescripcion_movimiento());
					item.setSaldoDisponible(x.getSaldo_actual_movimiento());
					retorno.add(item);
				}
			});
		}
		return retorno;
	}

	@Override
	public int crearMovimiento(Transaccion nuevo) throws Exception {
		int retorno = 0;
		Cuenta transaccion = new Cuenta();

		try {
			double saldoActual = 0;
			double saldoAnterior = 0;
			transaccion = cuentaService.buscarDatos(nuevo);
			if (Objects.nonNull(transaccion)) {
				String cedula = "";

				if (nuevo.getCedula().isEmpty()) {
					cedula = transaccion.getCliente().getPersona().getIdentificacion_persona();
				} else {
					cedula = nuevo.getCedula();
				}
				Movimiento elemento = new Movimiento();
				if (nuevo.getTipoMovimiento().equals("debito")) {
					if (validarMontoDiario(transaccion.getId_cuenta())) {
						if (transaccion.getSaldo_inicial_cuenta() >= 0) {
							if (nuevo.getMonto() <= transaccion.getSaldo_inicial_cuenta() && nuevo.getMonto() < 1000) {
								saldoAnterior = transaccion.getSaldo_inicial_cuenta();
								saldoActual = transaccion.getSaldo_inicial_cuenta() - nuevo.getMonto();
								Cuenta editar = new Cuenta();
								editar.setId_cuenta(transaccion.getId_cuenta());
								editar.setId_cliente(transaccion.getId_cliente());
								editar.setNumero_cuenta(transaccion.getNumero_cuenta());
								editar.setTipo_cuenta(transaccion.getTipo_cuenta());
								editar.setSaldo_inicial_cuenta(saldoActual);
								editar.setEstado_cuenta(1);
								editar.setCliente(transaccion.getCliente());
								cuentaService.editarCuenta(editar, cedula);
								elemento.setId_cuenta(transaccion.getId_cuenta());
								elemento.setFecha_movimiento(new Date(System.currentTimeMillis()));
								elemento.setTipo_movimiento(nuevo.getTipoMovimiento());
								elemento.setSaldo_movimiento(saldoAnterior);
								elemento.setEstado_movimiento(1);
								elemento.setDescripcion_movimiento("Retiro de: " + nuevo.getMonto());
								elemento.setSaldo_actual_movimiento(saldoActual);
								movimientoRepository.save(elemento);
								retorno = 1;
							}
						}
					}
				} else if (nuevo.getTipoMovimiento().equals("ahorro")) {
					saldoAnterior = transaccion.getSaldo_inicial_cuenta();
					saldoActual = transaccion.getSaldo_inicial_cuenta() + nuevo.getMonto();
					Cuenta editar = new Cuenta();
					editar.setId_cuenta(transaccion.getId_cuenta());
					editar.setId_cliente(transaccion.getId_cliente());
					editar.setNumero_cuenta(transaccion.getNumero_cuenta());
					editar.setTipo_cuenta(transaccion.getTipo_cuenta());
					editar.setSaldo_inicial_cuenta(saldoActual);
					editar.setEstado_cuenta(1);
					cuentaService.editarCuenta(editar,
							transaccion.getCliente().getPersona().getIdentificacion_persona());
					elemento.setId_cuenta(transaccion.getId_cuenta());
					elemento.setFecha_movimiento(new Date(System.currentTimeMillis()));
					elemento.setTipo_movimiento(nuevo.getTipoMovimiento());
					elemento.setSaldo_movimiento(nuevo.getMonto());
					elemento.setEstado_movimiento(1);
					elemento.setDescripcion_movimiento("Deposito de: " + nuevo.getMonto());
					elemento.setSaldo_actual_movimiento(saldoActual);
					movimientoRepository.save(elemento);
					retorno = 1;
				}
			}

		} catch (Exception e) {
			throw new Exception("No se pudo hacer la Trasaccion!!");
		}
		return retorno;
	}

	public boolean validarMontoDiario(long idCuenta) {
		boolean retorno = false;
		double monto = 0;
		Date hoy = new Date(System.currentTimeMillis());
		List<Movimiento> items = movimientoRepository.historialMovimientos(idCuenta);
		for (Movimiento movimiento : items) {
			if (movimiento.getFecha_movimiento() == hoy) {
				monto = monto + movimiento.getSaldo_movimiento();
			}
		}
		if (monto < 1000) {
			retorno = true;
		}
		return retorno;
	}
}
