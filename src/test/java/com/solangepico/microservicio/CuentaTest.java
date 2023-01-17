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

import com.solangepico.microservicio.Repository.CuentaRepository;
import com.solangepico.microservicio.Entity.*;

/**
 * @author SolangePico
 * @version 1.0.0 17/01/2023 ClaseTest que verificara los metodos de Cuenta.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CuentaTest {

	@Autowired
	private CuentaRepository cuentaRepo;

	@Test
	@Rollback(false)
	public void testGuardarCuenta() {
		Cuenta cuenta = new Cuenta(1, 1313131314, "2", 500, 1);
		Cuenta cuentaGuar = cuentaRepo.save(cuenta);
		assertNotNull(cuentaGuar);
	}
}
