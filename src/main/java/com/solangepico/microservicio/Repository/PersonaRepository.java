package com.solangepico.microservicio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.solangepico.microservicio.Entity.Persona;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene el CRUD con Spring JPA para la tabla de persona.
 */
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Query(value = "Select * FROM Persona p WHERE p.identificacion_persona = ?1", nativeQuery = true)
	Persona personaIdentificacion(String identificacion);
}
