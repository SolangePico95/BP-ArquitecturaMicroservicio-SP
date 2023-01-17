package com.solangepico.microservicio.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.solangepico.microservicio.Entity.Cuenta;
import com.solangepico.microservicio.Service.CuentaService;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 
 * Controlador que manipula el flujo de los servicios
 *          rest del microservicio de cuentas.
 */
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("api")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;
	
	@PostMapping("/cuenta/crearCuenta")
    public String crear(@RequestBody Cuenta elemento)
    {		
		try {
			cuentaService.crearCuenta(elemento);			
		}catch (Exception e) {
			throw new RuntimeException("Error al insertar la cuenta: "+elemento.getNumero_cuenta());
		}
        return "Cuenta creada.";
    }


    @GetMapping("/cuenta/listarCuentas")
    public List<Cuenta> listaCuentas()
    {
    	List<Cuenta> listaRetorno = new ArrayList<Cuenta>();
    	try {
    		listaRetorno = cuentaService.listarCuenta();
		} catch (Exception e) {
			throw new RuntimeException("No existen cuentas.");
		}
        return listaRetorno;
    }
 
    @PutMapping("/cuenta/editarCuenta/{id}")
    public String editar(@RequestBody Cuenta cuentaEdit,@PathVariable("id") String id)
    {
    	try {
    		cuentaService.editarCuenta(cuentaEdit,id);
		} catch (Exception e) {
			throw new RuntimeException("Error al editar la cuenta: "+ cuentaEdit.getNumero_cuenta());
		}
        return "Cuenta actualizado.";
    }
 
    @DeleteMapping("/cuenta/eliminarCuenta/{id}")
    public String eliminar(@PathVariable("id") int id)
    {
        try {
			cuentaService.eliminarCuenta(id);
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar el registro: "+id);
		}
        return "Cuenta eliminada.";
    }
}
