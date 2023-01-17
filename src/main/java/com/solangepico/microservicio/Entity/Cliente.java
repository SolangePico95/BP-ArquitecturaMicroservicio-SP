package com.solangepico.microservicio.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 Clase que representa entidades de cliente.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private long id_cliente;
	@Column(name = "CONTRASENA_CLIENTE")
	private String contrasena_cliente;
	@Column(name = "ESTADO_CLIENTE")
	private int estado_cliente;
	@Column(name = "ID_PERSONA")
	private long id_persona;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_persona", insertable = false, updatable = false)
	private Persona persona;

	public Cliente() {
		super();
	}

	public long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getContrasena_cliente() {
		return contrasena_cliente;
	}

	public void setContrasena_cliente(String contrasena_cliente) {
		this.contrasena_cliente = contrasena_cliente;
	}

	public int getEstado_cliente() {
		return estado_cliente;
	}

	public void setEstado_cliente(int estado_cliente) {
		this.estado_cliente = estado_cliente;
	}

	public long getId_persona() {
		return id_persona;
	}

	public void setId_persona(long id_persona) {
		this.id_persona = id_persona;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", contrasena_cliente=" + contrasena_cliente + ", estado_cliente="
				+ estado_cliente + ", persona=" + persona + "]";
	}
}
