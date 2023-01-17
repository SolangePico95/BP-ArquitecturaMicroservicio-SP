package com.solangepico.microservicio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.solangepico.microservicio.Entity.Cliente;
import com.solangepico.microservicio.Entity.Cuenta;

import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023
 * Interface que contiene el CRUD con Spring JPA para la tabla de cuenta.
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	
	@Query(value = "Select * FROM cuenta c "
			+ " inner join cliente cl on cl.ID_CLIENTE = c.ID_CLIENTE "
			+ " inner join persona p on cl.ID_PERSONA = p.ID_PERSONA "
			+ " WHERE p.identificacion_persona = ?1 "
			+ " AND c.TIPO_CUENTA = ?2 "
			+ " AND c.ESTADO_CUENTA = 1",nativeQuery = true)
    Cuenta cuentaIdPersona(String identificacion, String tipoCuenta);	
	
	@Query(value = "Select * FROM Cuenta c "
			+ " WHERE c.numero_cuenta = ?1",nativeQuery = true)
    Cuenta cuentaxNumeroCuenta(Integer numeroCuenta);	
		
}
