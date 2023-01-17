package com.solangepico.microservicio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solangepico.microservicio.Entity.Cliente;
import com.solangepico.microservicio.Entity.Estado;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene el CRUD con Spring JPA para la tabla de estado.
 */
public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
	@Query(value = "Select * FROM Estado c "
			+ " WHERE p.descripcion_estado LIKE ?1%",nativeQuery = true)
    Estado estadoDescripcion(String identificacion);	
	
}