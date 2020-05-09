package main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entity.Autor;
import entity.Genero;
import entity.Libro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScrapingEbookelo {
	public static List<Libro> leerPagina(int numeroDePaginas) {
		String urlToRead;
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        Long libroId = 0l;
        List<Libro> result = new ArrayList<>();
        for(int i = 1; i <= numeroDePaginas; i++) {
        	urlToRead = "https://www.ebookelo.com/ebooks/espanol/page/" + i;
	        try {
	            url = new URL(urlToRead);
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
	            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0");
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            while ((line = rd.readLine()) != null) {
	            	if(line.contains("href=\"/ebook") && !line.contains("pageLink")) {
	            		line = line.substring(line.indexOf("href") + 6);
	            		line = line.substring(0, line.indexOf("\""));
	            		line = "https://www.ebookelo.com" + line;
	            		Thread.sleep(500);
	            		
	            		URL url2;
	            		HttpURLConnection conn2;
	            		BufferedReader rd2;
	            		String line2;
	            		
	            		try {
	            			url2 = new URL(line);
	                        conn2 = (HttpURLConnection) url2.openConnection();
	                        conn2.setRequestMethod("GET");
	                        conn2.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
	                        conn2.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0");
	                        rd2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
	                        
	                        Libro libro = new Libro(Long.parseLong(line.substring(line.indexOf("ebook/") + 6, line.lastIndexOf("/"))));
	                        
	                        while ((line2 = rd2.readLine()) != null) {
	                        	if(line2.trim().startsWith("<div class=\"bookField")) {
	                        		line2 = line2.substring(line2.indexOf("bookField") + 9);
	                        		
	                        		if(line2.contains("autor")) {
	                        			line2 = line2.substring(line2.indexOf("page/"), line2.lastIndexOf("</a>"));
	                        			line2 = line2.substring(line2.indexOf(">") + 1);
	                        			libro.setAutor(new Autor(line2));
	                        		}
	                        		else if(line2.contains("Páginas")) {
	                        			line2 = line2.substring(line2.indexOf("Páginas:") + 9, line2.lastIndexOf("</div>"));
	                        			libro.setPaginas(Integer.parseInt(line2));
	                        		}
	                        		else if(line2.contains("Valoración")) {
	                        			line2 = line2.substring(line2.indexOf("Valoración") + 11, line2.lastIndexOf(" de"));
	                        			libro.setValoracion(Double.parseDouble(line2));
	                        		}
	                        		else if(line2.contains("Idioma")) {
	                        			line2 = line2.substring(line2.indexOf("Idioma: ") + 8, line2.lastIndexOf(" <span"));
	                        			libro.setIdioma(line2);
	                        		}
	                        		else if(line2.contains("Publicado en")) {
	                        			line2 = line2.substring(line2.indexOf("Publicado en: ") + 14, line2.lastIndexOf("</div>"));
	                        			libro.setAnyoPublicacion(Integer.parseInt(line2));
	                        		}
	                        		else if(line2.contains("genero/")) {
	                        			int pos = line2.indexOf("genero/");
	                        			line2 = line2.substring(pos + 1);
	                        			
	                        			while(pos != -1) {
	                        				String aux = line2.substring(line2.indexOf(">") + 1, line2.indexOf("</a>"));
	                        				libro.addGenero(aux);
	                        				pos = line2.indexOf("genero/");
	                        				line2 = line2.substring(pos + 1);
	                        			}
	                        		}
	                        	}
	                        	else if(line2.trim().startsWith("<h1>")) {
	                        		line2 = line2.substring(line2.indexOf(">") + 1, line2.lastIndexOf("<"));
	                        		libro.setTitulo(line2);
	                        	}
	                        }
	                        
	                        result.add(libro);
	                        System.out.println(libroId + " " + libro);
	                        libroId++;
	            		} catch (Exception e) {
	                        e.printStackTrace();
	                    }
	            		
	            		Thread.sleep(500);
	            	}
	            }
	            rd.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
        
        return result;
	}
	
	public static void guardarListaDeLibros(List<Libro> result) {
		PrintWriter pw = null;
        
        try
        {
            pw = new PrintWriter("librosAux.txt");
            
            for(Libro l: result) {
            	pw.println(l);
            }
            
            System.out.println("File created");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
	}
	
	public static List<Libro> depurarListaDeLibros() {
		Path path = Paths.get("librosAux.txt");
        List<Libro> libros = new ArrayList<>();
		try {
			libros = Files.readAllLines(path)
					.stream()
					.filter(l -> !l.contains("<"))
					.distinct()
					.map(l -> {
						String[] libroAux = l.split("\\|");
						Libro libro = new Libro(Long.parseLong(libroAux[0]));
						libro.setTitulo(libroAux[1]);
						libro.setAutor(new Autor(libroAux[2]));
						libro.setIdioma(libroAux[3]);
						libro.setPaginas(Integer.parseInt(libroAux[4]));
						libro.setAnyoPublicacion(Integer.parseInt(libroAux[5]));
						try {
							libro.setValoracion(Double.parseDouble(libroAux[6]));
						} catch(Exception e) {
							libro.setValoracion(null);
						}
						
						for(int i = 7; i < libroAux.length; i++) {
							libro.addGenero(libroAux[i]);
						}
						
						return libro;
					})
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return libros;
	}
	
	public static List<Genero> getGeneros(List<Libro> libros) {
		List<Genero> generos = new ArrayList<>();
        
        for(Libro l: libros) {
        	
        	l.getGeneros().forEach(g -> {
        		if(!generos.contains(g)) {
        			generos.add(g);
        		}
        	});
        }
        
        return generos;
	}
	
	public static List<Autor> getAutores(List<Libro> libros) {
		List<Autor> autores = new ArrayList<>();
        
        for(Libro l: libros) {
        	if(!autores.contains(l.getAutor())) {
        		autores.add(l.getAutor());
        	}
        }
        
        return autores;
	}
	
	public static void guardarListaGenerica(List<?> lista, String nombreArchivo) {
		PrintWriter pw = null;
        
        try
        {
            pw = new PrintWriter(nombreArchivo + ".txt");
            
            lista.forEach(pw::println);
            
            System.out.println("Archivo creado");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
	}
	
	public static void crearSQL(List<Genero> generos, List<Autor> autores, List<Libro> libros) {
		PrintWriter pw = null;
		
		try
        {
            pw = new PrintWriter("import.sql");
            
            for(Genero g: generos) {
            	pw.println("INSERT INTO generos (nombre) VALUES ('" +
            			g.getNombre() + "');");
            }
            
            pw.println();
            
            for(Autor a: autores) {
            	pw.println("INSERT INTO autores (nombre) VALUES ('" +
            			a.getNombre() + "');");
            }
            
            for(Libro l: libros) {
            	pw.println("INSERT INTO libros (id_foto, titulo, id_autor, " +
            			"idioma, paginas, anyo_publicacion, valoracion) " +
            			"VALUES (" + l.getIdFoto() + ", '" + l.getTitulo() +
            			"', (SELECT id FROM autores WHERE nombre = '" +
            			l.getAutor() + "'), '" + l.getIdioma() + "', " +
            			l.getPaginas() + ", " + l.getAnyoPublicacion() +
            			", " + l.getValoracion() + ");");
            	
            	for(Genero g: l.getGeneros()) {
            		pw.println("INSERT INTO libros_generos (id_libro, " + 
            				"id_genero) VALUES ((SELECT id FROM libros WHERE " + 
            				"id_foto = " + l.getIdFoto() + "), (SELECT id FROM " + 
            				"generos WHERE nombre = '" + g.getNombre() +"'));");
            	}
            }
            
            System.out.println("SQL creado");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
	}
	
	public static void main(String[] args) throws IOException {
		//List<Libro> resultado = leerPagina(1678);
		
		//guardarListaDeLibros(resultado);
        
        List<Libro> libros = depurarListaDeLibros();
        
        List<Genero> generos = getGeneros(libros);
        
        List<Autor> autores = getAutores(libros);
        
        guardarListaGenerica(generos, "generos");
        guardarListaGenerica(autores, "autores");
        guardarListaGenerica(libros, "libros");
        
        crearSQL(generos, autores, libros);
	}
}
