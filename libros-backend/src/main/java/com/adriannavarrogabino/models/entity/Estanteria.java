package com.adriannavarrogabino.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "estanterias")
public class Estanteria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El nombre de la estantería no puede estar vacío")
	@Column(nullable = false)
	private String nombre;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "estanterias_libros",
		joinColumns = @JoinColumn(name = "estanteria_id"),
		inverseJoinColumns = @JoinColumn(name = "libro_id"),
		uniqueConstraints = {@UniqueConstraint(columnNames = {"estanteria_id", "libro_id"})})
	private List<Libro> libros;

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

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public void addLibro(Libro libro) {
		this.libros.add(libro);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
