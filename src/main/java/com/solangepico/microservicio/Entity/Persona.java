package com.solangepico.microservicio.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SolangePico
 * @version 1.0.0 16/01/2023 Clase que representa entidad persona.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PERSONA")
	private long id_persona;
	@Column(name = "NOMBRE_PERSONA")
	private String nombre_persona;
	@Column(name = "EDAD_PERSONA")
	private int edad_persona;
	@Column(name = "IDENTIFICACION_PERSONA")
	private String identificacion_persona;
	@Column(name = "DIRECCION_PERSONA")
	private String direccion_persona;
	@Column(name = "telefono_PERSONA")
	private String telefono_persona;
	@Column(name = "GENERO_PERSONA")
	private String genero_persona;

	public Persona() {
		super();
	}

	public long getId_persona() {
		return id_persona;
	}

	public void setId_persona(long id_persona) {
		this.id_persona = id_persona;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}

	public int getEdad_persona() {
		return edad_persona;
	}

	public void setEdad_persona(int edad_persona) {
		this.edad_persona = edad_persona;
	}

	public String getIdentificacion_persona() {
		return identificacion_persona;
	}

	public void setIdentificacion_persona(String identificacion_persona) {
		this.identificacion_persona = identificacion_persona;
	}

	public String getDireccion_persona() {
		return direccion_persona;
	}

	public void setDireccion_persona(String direccion_persona) {
		this.direccion_persona = direccion_persona;
	}

	public String getTelefono_persona() {
		return telefono_persona;
	}

	public void setTelefono_persona(String telefono_persona) {
		this.telefono_persona = telefono_persona;
	}

	public String getGenero_persona() {
		return genero_persona;
	}

	public void setGenero_persona(String genero_persona) {
		this.genero_persona = genero_persona;
	}

	@Override
	public String toString() {
		return "Persona [id_persona=" + id_persona + ", nombre_persona=" + nombre_persona + ", edad_persona="
				+ edad_persona + ", identificacion_persona=" + identificacion_persona + ", DIRRECCION_persona="
				+ direccion_persona + ", TELEFONO_persona=" + telefono_persona + ", genero_persona=" + genero_persona
				+ "]";
	}
}
