package com.solangepico.microservicio.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solangepico.microservicio.Entity.Estado;
import com.solangepico.microservicio.Repository.EstadoRepository;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene los metodos de logica de negocio para estado.
 */
@Service
public class EstadoService implements IEstadoService{

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public Estado buscarEstado(String descripcion) {
		Estado elemento = new Estado();
		elemento = estadoRepository.estadoDescripcion(descripcion);
		return elemento;
	}

	@Override
	public List<Estado> listarEstados() {
		List<Estado> lista = new ArrayList<Estado>();
		lista = estadoRepository.findAll();
		return lista;
	}

	@Override
	public Estado crearEstado(Estado nueva) throws Exception {
		Estado retorno = new Estado();
		try {
			var estado = new Estado();
			estado = estadoRepository.save(nueva);
			if (Objects.nonNull(estado)) {
				retorno = estado;
			}
		} catch (Exception e) {
			throw new Exception("No se pudo crear el Registro!!");
		}
		return retorno;
	}

	@Override
	public Boolean editarEstado(Estado editar) throws Exception {
		boolean retorno = false;
		try {
			Estado depDB = estadoRepository.estadoDescripcion(editar.getDescripcion_estado());
			if (Objects.nonNull(depDB)) {				
				if (Objects.nonNull(editar.getDescripcion_estado()) && !"".equalsIgnoreCase(editar.getDescripcion_estado())) {
					depDB.setDescripcion_estado(editar.getDescripcion_estado());
				}
				estadoRepository.save(depDB);
				retorno = true;
			}
			else {
				throw new Exception("Existe mas de un registro con ese numero de cedula");
			}
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el Registro!!");
		}
		return retorno;
	}

	@Override
	public Boolean eliminarEstado(String descripcion) throws Exception {
		boolean retorno = false;
		try {
			Estado depDB = estadoRepository.estadoDescripcion(descripcion);
			if (Objects.nonNull(depDB)) {
				estadoRepository.delete(depDB);
				retorno = true;
			}
			else {
				throw new Exception("Existe mas de un registro con ese numero de cedula");
			}
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el Registro!!");
		}
		return retorno;
	}
	

}
