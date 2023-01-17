/**
 * 
 */
package com.solangepico.microservicio;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.solangepico.microservicio.Entity.Cuenta;
import com.solangepico.microservicio.Entity.Persona;
import com.solangepico.microservicio.Repository.PersonaRepository;

/**
 * @author SolangePico
 * @version 1.0.0 17/01/2023 ClaseTest que verificara los metodos de Persona..
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonaTest {

	@Autowired
	private PersonaRepository personaRepo;

	@Test
	@Rollback(false)
	public void testGuardarPersona() {
		Persona perso = new Persona("Paul McCartney", 40, "1756789567", "Masculino", "0987654323", "NewYork");
		Persona persoGuar = personaRepo.save(perso);
		assertNotNull(persoGuar);
	}
}
