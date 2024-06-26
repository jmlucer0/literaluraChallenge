package com.aluracurso.biblioteca;

import com.aluracurso.biblioteca.model.DatosAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aluracurso.biblioteca.service.ConsumirAPI;
import com.aluracurso.biblioteca.service.ConvertirDatos;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String BASE_URL = "https://gutendex.com/books/";
		ConsumirAPI consumirAPI = new ConsumirAPI();
		ConvertirDatos convertirDatos = new ConvertirDatos();

		var json = consumirAPI.obternerJson(BASE_URL);
		DatosAPI datosAPI = convertirDatos.obtenerDatos(json, DatosAPI.class);
		System.out.println(datosAPI);
	}
}
