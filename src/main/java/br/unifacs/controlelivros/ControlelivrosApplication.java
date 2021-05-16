package br.unifacs.controlelivros;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.unifacs.controlelivros.model.Livro;
import br.unifacs.controlelivros.repository.BibliotecaRepository;

@SpringBootApplication
public class ControlelivrosApplication implements CommandLineRunner {

	@Autowired
	private BibliotecaRepository bibliotecaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ControlelivrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Livro livro1 = new Livro(null, "Java", "Editora A", 10, "Programação Java", "Autor A", "1");
		Livro livro2 = new Livro(null, "JavaScript", "Editora B", 10, "Programação JavaScript", "Autor B", "2");
		Livro livro3 = new Livro(null, "HTML", "Editora C", 10, "Programação HTML", "Autor C", "3");
		Livro livro4 = new Livro(null, "CSS", "Editora D", 10, "Programação CSS", "Autor D", "4");
		Livro livro5 = new Livro(null, "React", "Editora E", 10, "Programação React", "Autor E", "5");

		bibliotecaRepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5));
	}

}
