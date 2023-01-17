package com.solangepico.microservicio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solangepico.microservicio.Entity.Cliente;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene el CRUD con Spring JPA para la tabla de cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query(value = "Select * FROM Cliente c "
			+ " inner join Persona p on c.id_persona = p.id_persona"
			+ " WHERE p.identificacion_persona = ?1",nativeQuery = true)
    Cliente clienteIdPersona(String identificacion);	
	
}
