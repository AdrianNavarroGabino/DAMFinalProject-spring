package com.adriannavarrogabino.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El nombre no puede estar vacío")
	@Size(min = 3, max = 25, message = "El tamaño del nombre tiene que estar entre 3 y 25")
	@Column(nullable = false)
	private String nombre;

	@NotEmpty(message = "Los apellidos no pueden estar vacíos")
	@Size(min = 3, max = 25, message = "El tamaño de los apellidos tiene que estar entre 3 y 25")
	@Column(nullable = false)
	private String apellidos;

	@NotEmpty(message = "El usuario no puede estar vacío")
	@Size(min = 3, max = 20, message = "El tamaño del nombre tiene que estar entre 3 y 20")
	@Column(nullable = false, unique = true)
	private String username;

	@NotEmpty(message = "El email no puede estar vacío")
	@Email(message = "El email no es una dirección de correo bien formada")
	@Column(nullable = false, unique = true)
	private String correo;

	@NotEmpty(message = "La contraseña no puede estar vacía")
	@Size(min = 4, message = "El tamaño de la contraseña tiene que ser mayor que 3")
	@Column(nullable = false)
	private String password;

	@Column(name = "fecha_nacimiento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Column(name = "ultimo_acceso")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	@Temporal(TemporalType.DATE)
	private Date ultimoAcceso;

	@Column(name = "acceso_actual")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	@Temporal(TemporalType.DATE)
	private Date accesoActual;

	@ManyToMany(mappedBy = "usuarios")
	private Set<Grupo> grupos = new HashSet(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public Date getAccesoActual() {
		return accesoActual;
	}

	public void setAccesoActual(Date accesoActual) {
		this.accesoActual = accesoActual;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
