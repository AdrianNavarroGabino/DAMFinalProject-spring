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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "libros")
public class Libro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El título no puede estar vacío")
	@Column(nullable = false)
	private String titulo;

	/*
	 * Habrá libros que solo tengan isbn10 o isbn13, o que tengan los 2. Si les
	 * pongo la anotación de UK a los dos campos, ¿habrá problemas? INVESTIGAR
	 */
	@Size(min = 10, max = 10)
	@Column(length = 10)
	private String isbn10;

	@Size(min = 13, max = 13)
	@Column(length = 13)
	private String isbn13;

	private String editorial;

	@Column(name = "fecha_publicacion")
	private String fechaPublicacion;

	@ManyToMany(mappedBy = "libros")
	private Set<Autor> autores = new HashSet<Autor>(0);

	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
