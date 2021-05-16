package br.unifacs.controlelivros.service;

import java.util.List;

import br.unifacs.controlelivros.dto.LivroDTO;

public interface BibliotecaService {

	void adicionarLivro(LivroDTO livro);

	List<LivroDTO> listarTodos();

	void removerLivro(LivroDTO livro);

	LivroDTO buscarLivro(Integer id);
	
	LivroDTO buscarLivroPorNome(String nome);

	List<LivroDTO> listarTodosOrdenarEditora();

}
