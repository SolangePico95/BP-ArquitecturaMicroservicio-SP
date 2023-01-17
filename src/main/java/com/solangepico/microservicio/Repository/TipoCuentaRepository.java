package com.solangepico.microservicio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solangepico.microservicio.Entity.Cliente;
import com.solangepico.microservicio.Entity.Estado;
import com.solangepico.microservicio.Entity.TipoCuenta;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene el CRUD con Spring JPA para la tabla de tipocuenta.
 */
public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Long> {
	
	@Query(value = "Select * FROM TipoCuenta t "
			+ " WHERE t.descripcion_TipoCuenta LIKE ?1%",nativeQuery = true)
    TipoCuenta tipoCuentaDescripcion(String identificacion);		
}
