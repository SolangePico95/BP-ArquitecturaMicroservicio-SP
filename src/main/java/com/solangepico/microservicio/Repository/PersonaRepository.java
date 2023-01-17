package com.solangepico.microservicio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.solangepico.microservicio.Entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Query(value = "Select * FROM Persona p WHERE p.identificacion_persona = ?1", nativeQuery = true)
	Persona personaIdentificacion(String identificacion);
}
