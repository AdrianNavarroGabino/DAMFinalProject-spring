// Adrián Navarro Gabino

package entity;
import java.util.ArrayList;
import java.util.List;

public class Libro {
	private Long id;
	private Long idFoto;
	private String titulo;
	private Autor autor;
	private List<Genero> generos;
	private String idioma;
	private Integer paginas;
	private Integer anyoPublicacion;
	private Double valoracion;
	
	public Libro(Long idFoto) {
		
		this.idFoto = idFoto;
		generos = new ArrayList<>();
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

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> genero) {
		this.generos = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
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
	
	public void addGenero(String g) {
		generos.add(new Genero(g));
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

	@Override
	public String toString() {
		String result = idFoto + "|" + titulo + "|" + autor + "|" + idioma
				+ "|" + paginas + "|" + anyoPublicacion + "|" + valoracion;
		
		for(Genero g: generos) {
			result += "|" + g.getNombre();
		}
		
		return result;
	}
}
