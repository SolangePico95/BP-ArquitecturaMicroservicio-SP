package com.solangepico.microservicio.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solangepico.microservicio.Entity.Cuenta;
import com.solangepico.microservicio.Entity.Transaccion;
import com.solangepico.microservicio.Repository.CuentaRepository;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene los metodos de logica de negocio para cuenta.
 */
@Service
public class CuentaService implements ICuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;
	@Autowired
	private ClienteService clienteService;

	@Override
	public Cuenta buscarCuenta(String identificacionPersona) {
		var cuenta = new Cuenta();
		cuenta = cuentaRepository.cuentaIdPersona(identificacionPersona, cuenta.getTipo_cuenta());
		return cuenta;
	}

	@Override
	public List<Cuenta> listarCuenta() {
		List<Cuenta> lista = new ArrayList<Cuenta>();
		lista = cuentaRepository.findAll();
		return lista;
	}

	@Override
	public Cuenta crearCuenta(Cuenta nueva) throws Exception {
		Cuenta retorno = new Cuenta();
		try {
			var cuenta = new Cuenta();
			var cliente = nueva.getCliente();
			var persona = cliente.getPersona();
			var ClienteRep = clienteService.buscarCliente(persona.getIdentificacion_persona());
			if (!Objects.nonNull(ClienteRep)) {
				var nuevoCliente = clienteService.crearCliente(cliente);
				nueva.setId_cliente(nuevoCliente.getId_persona());
				cuenta = cuentaRepository.save(nueva);
				if (Objects.nonNull(cuenta)) {
					retorno = cuenta;
				}
			} else {
				nueva.setId_cliente(ClienteRep.getId_cliente());
				cuenta = cuentaRepository.save(nueva);
				if (Objects.nonNull(cuenta)) {
					retorno = cuenta;
				}
			}
		} catch (Exception e) {
			throw new Exception("No se pudo crear el Registro!!");
		}
		return retorno;
	}

	@Override
	public Boolean editarCuenta(Cuenta editar, String identificacion) throws Exception {
		boolean retorno = false;
		try {

			Cuenta depDB = cuentaRepository.cuentaIdPersona(identificacion, editar.getTipo_cuenta());

			if (Objects.nonNull(depDB)) {
				clienteService.editarCliente(depDB.getCliente(), identificacion);
				if (Objects.nonNull(editar.getEstado_cuenta())) {
					depDB.setEstado_cuenta(editar.getEstado_cuenta());
				}

				if (Objects.nonNull(editar.getTipo_cuenta()) && !"".equalsIgnoreCase(editar.getTipo_cuenta())) {
					depDB.setTipo_cuenta(editar.getTipo_cuenta());
				}

				if (Objects.nonNull(editar.getNumero_cuenta()) || editar.getNumero_cuenta() != 0) {
					depDB.setNumero_cuenta(editar.getNumero_cuenta());
				}

				if (Objects.nonNull(editar.getSaldo_inicial_cuenta()) || editar.getSaldo_inicial_cuenta() != 0) {
					depDB.setSaldo_inicial_cuenta(editar.getSaldo_inicial_cuenta());
				}

				cuentaRepository.save(depDB);
				retorno = true;
			} else {
				throw new Exception("Existe mas de un registro con ese numero de cedula");
			}
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el Registro!!");
		}
		return retorno;
	}

	@Override
	public Boolean eliminarCuenta(int numeroCuenta) throws Exception {
		boolean retorno = false;
		try {
			Cuenta eliminar = new Cuenta();
			eliminar = cuentaRepository.cuentaxNumeroCuenta(numeroCuenta);
			if (Objects.nonNull(eliminar)) {
				eliminar.setEstado_cuenta(2);
				cuentaRepository.save(eliminar);
				retorno = true;
			} else {
				throw new Exception("Existe mas de un registro con ese numero de cedula");
			}
			retorno = true;
		} catch (Exception e) {
			throw new Exception("No se pudo elminar el Registro!!");
		}
		return retorno;
	}

	@Override
	public Cuenta buscarDatos(Transaccion datos) {
		Cuenta retorno = new Cuenta();
		retorno = cuentaRepository.cuentaxNumeroCuenta(datos.getNumero_Cuenta());
		if (Objects.nonNull(retorno)) {
			if (datos.getNumero_Cuenta() == retorno.getNumero_cuenta()
					&& datos.getCedula() == retorno.getCliente().getPersona().getIdentificacion_persona()) {
				return retorno;
			} else if (datos.getCedula() == retorno.getCliente().getPersona().getIdentificacion_persona()
					&& datos.getContraseña() == retorno.getCliente().getContrasena_cliente()) {
				return retorno;
			}
		}
		return retorno;
	}

}
