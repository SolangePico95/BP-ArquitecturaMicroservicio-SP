package com.solangepico.microservicio.Entity;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 Clase que representa las transacciones a realizar.
 */
public class Transaccion {

	private String cedula;
	private String contraseña;
	private int numero_Cuenta;
	private String tipoMovimiento;
	private double monto;
	private int tipoCuenta;

	public Transaccion() {
		super();
	}

	public Transaccion(String cedula, String contraseña, int numero_Cuenta, String tipoMovimiento, double monto,
			int tipoCuenta) {
		super();
		this.cedula = cedula;
		this.contraseña = contraseña;
		this.numero_Cuenta = numero_Cuenta;
		this.tipoMovimiento = tipoMovimiento;
		this.monto = monto;
		this.tipoCuenta = tipoCuenta;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getNumero_Cuenta() {
		return numero_Cuenta;
	}

	public void setNumero_Cuenta(int numero_Cuenta) {
		this.numero_Cuenta = numero_Cuenta;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
}
