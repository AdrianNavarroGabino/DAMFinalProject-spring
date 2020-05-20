package com.adriannavarrogabino.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "libros")
public class Libro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_foto")
	private Long idFoto;

	@NotEmpty(message = "El título no puede estar vacío")
	@Column(nullable = false)
	private String titulo;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_autor")
	private Autor autor;

	private String idioma;

	@Column(name = "anyo_publicacion")
	private Integer anyoPublicacion;

	private Double valoracion;

	private Integer paginas;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "libros_generos",
		joinColumns = @JoinColumn(name = "id_libro"),
		inverseJoinColumns = @JoinColumn(name = "id_genero"),
		uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_libro", "id_genero" }) })
	private List<Genero> generos;

	@PrePersist
	public void prepersist() {
		generos = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Integer getAnyoPublicacion() {
		return anyoPublicacion;
	}

	public void setAnyoPublicacion(Integer anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public void addGenero(Genero genero) {
		this.generos.add(genero);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
