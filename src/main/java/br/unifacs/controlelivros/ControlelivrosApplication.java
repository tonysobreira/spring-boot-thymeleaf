package br.unifacs.controlelivros;

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
		Livro livro = null;

		for (int i = 0; i < 5; i++) {
			livro = new Livro(null, "Livro " + i, "Editora " + i, i, "Área " + i);
			bibliotecaRepository.save(livro);
		}

	}

}
