package com.solangepico.microservicio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solangepico.microservicio.Entity.Cliente;
import com.solangepico.microservicio.Entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
	@Query(value = "Select * FROM Estado c "
			+ " WHERE p.descripcion_estado LIKE ?1%",nativeQuery = true)
    Estado estadoDescripcion(String identificacion);	
	
}