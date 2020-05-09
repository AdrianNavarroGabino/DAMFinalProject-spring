package main;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ScrapingIsbndb {
	public static final String url = "https://isbndb.com/book/";

    public static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
    }
    
    public static void main(String[] args)  throws Exception
    {

        PrintWriter pw = null;
        Set<String> autores = new HashSet<>();

        try
        {
            pw = new PrintWriter(new BufferedWriter(
                                new FileWriter("import.sql", true)));

            for(int i = 0; i <= 9999999; i++)
            {
                String isbn = "84" + String.format("%07d", i);
                int control = 0;
                for(int j = 0; j < 9; j++)
                {
                    control += (Integer.parseInt(isbn.charAt(j) + "") * (j + 1));
                }

                control %= 11;

                if(control == 10)
                {
                    isbn += "X";
                }
                else
                {
                    isbn += control;
                }

                try
                {
                    String data = readUrl(url + isbn);
                    data = data.substring(data.indexOf("<table"), data.indexOf("</table"));

                    String titulo = data.substring(data.indexOf("Full Title</th> <td>") + "Full Title</th> <td>".length(), data.indexOf("</td>"));

                    String autorNombre, autorApellidos;
                    String dataAutores;
                    String[] nombresAutor, apellidosAutor;

                    String publicado = data.substring(data.indexOf("Publish Date"));
                    publicado = publicado.substring(publicado.indexOf("<td>") + 4, publicado.indexOf("</td>"));

                    String editorial = data.substring(data.lastIndexOf("publisher"));
                    editorial = editorial.substring(editorial.indexOf(">") + 1, editorial.indexOf("</a>"));

                    String isbn10 = data.substring(data.indexOf("<th>ISBN</td> <th>") + "<th>ISBN</td> <th>".length());
                    isbn10 = isbn10.substring(0, isbn10.indexOf("</td>"));

                    String isbn13 = data.substring(data.indexOf("<th>ISBN13</th> <td>") + "<th>ISBN13</th> <td>".length());
                    isbn13 = isbn13.substring(0, isbn13.indexOf("</td>"));

                    pw.println("INSERT INTO libros (titulo, isbn10, isbn13, " +
                        "editorial, fecha_publicacion) VALUES ('" + titulo +
                        "', '" + isbn10 + "', '" + isbn13 + "', '" + editorial +
                        "', '" + publicado + "');");

                    System.out.println("INSERT INTO libros (titulo, isbn10, isbn13, " +
                        "editorial, fecha_publicacion) VALUES ('" + titulo +
                        "', '" + isbn10 + "', '" + isbn13 + "', '" + editorial +
                        "', '" + publicado + "');");

                    if(data.indexOf("/author") != data.lastIndexOf("/author"))
                    {
                        autorNombre = data.substring(data.lastIndexOf("/author") + 8);
                        autorNombre = autorNombre.substring(autorNombre.indexOf(">") + 1, autorNombre.indexOf("</"));

                        autorApellidos = data.substring(data.indexOf("/author") + 8);
                        autorApellidos = autorApellidos.substring(autorApellidos.indexOf(">") + 1, autorApellidos.indexOf("</"));

                        if(!autores.contains(autorNombre + " " + autorApellidos))
                        {
                            autores.add(autorNombre + " " + autorApellidos);
                            pw.println("INSERT INTO autores (nombre, apellidos) " +
                                "VALUES ('" + autorNombre + "', '" +
                                autorApellidos + "');");
                            System.out.println("INSERT INTO autores (nombre, apellidos) " +
                                "VALUES ('" + autorNombre + "', '" +
                                autorApellidos + "');");
                        }

                        pw.println("INSERT INTO libros_autores (id_libro, id_autor) VALUES ((SELECT id FROM libros WHERE isbn10 = '" + isbn10 + "'), (SELECT id FROM autores WHERE nombre = '" + autorNombre + "' AND apellidos = '" + autorApellidos + "'));");

                        System.out.println("INSERT INTO libros_autores (id_libro, id_autor) VALUES ((SELECT id FROM libros WHERE isbn10 = '" + isbn10 + "'), (SELECT id FROM autores WHERE nombre = '" + autorNombre + "' AND apellidos = '" + autorApellidos + "'));");
                    }
                    else
                    {
                        dataAutores = data.substring(data.lastIndexOf("/author") + 8);
                        dataAutores = dataAutores.substring(dataAutores.indexOf(">") + 1, dataAutores.indexOf("</"));

                        nombresAutor = new String[dataAutores.split("; ").length];
                        apellidosAutor = new String[dataAutores.split("; ").length];

                        for(int j = 0; j < dataAutores.split("; ").length; j++)
                        {
                            apellidosAutor[j] = dataAutores.split("; ")[j].split(", ")[0];
                            nombresAutor[j] = dataAutores.split("; ")[j].split(", ")[1];

                            if(!autores.contains(nombresAutor[j] + " " + apellidosAutor[j]))
                            {
                                autores.add(nombresAutor[j] + " " + apellidosAutor[j]);
                                pw.println("INSERT INTO autores (nombre, apellidos) " +
                                    "VALUES ('" + nombresAutor[j] + "', '" +
                                    apellidosAutor[j] + "');");
                                System.out.println("INSERT INTO autores (nombre, apellidos) " +
                                    "VALUES ('" + nombresAutor[j] + "', '" +
                                    apellidosAutor[j] + "');");
                            }

                            pw.println("INSERT INTO libros_autores (id_libro, id_autor) VALUES ((SELECT id FROM libros WHERE isbn10 = '" + isbn10 + "'), (SELECT id FROM autores WHERE nombre = '" + nombresAutor[j] + "' AND apellidos = '" + apellidosAutor[j] + "'));");

                            System.out.println("INSERT INTO libros_autores (id_libro, id_autor) VALUES ((SELECT id FROM libros WHERE isbn10 = '" + isbn10 + "'), (SELECT id FROM autores WHERE nombre = '" + nombresAutor[j] + "' AND apellidos = '" + apellidosAutor[j] + "'));");
                        }
                    }
                    
                    System.out.println(isbn);
                }
                catch(Exception e)
                {

                }
            }
            
            System.out.println("File created");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
    }
}
