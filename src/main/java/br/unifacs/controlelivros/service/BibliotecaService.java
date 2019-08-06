package br.unifacs.controlelivros.service;

import java.util.List;

import br.unifacs.controlelivros.model.Livro;

public interface BibliotecaService {

	void adicionarLivro(Livro livro);

	List<Livro> listarTodos();

	void removerLivro(Livro livro);

	Livro buscarLivro(Integer id);
	
	Livro buscarLivroPorNome(String nome);

	List<Livro> listarTodosOrdenarEditora();

}
