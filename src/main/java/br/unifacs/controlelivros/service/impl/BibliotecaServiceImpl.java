package br.unifacs.controlelivros.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifacs.controlelivros.model.Livro;
import br.unifacs.controlelivros.repository.BibliotecaRepository;
import br.unifacs.controlelivros.service.BibliotecaService;

@Service("bibliotecaService")
public class BibliotecaServiceImpl implements BibliotecaService {

	@Autowired
	private BibliotecaRepository bibliotecaRepository;

	@Override
	public void adicionarLivro(Livro livro) {
		bibliotecaRepository.save(livro);
	}

	@Override
	public List<Livro> listarTodos() {
		return bibliotecaRepository.findAll();
	}

	@Override
	public void removerLivro(Livro livro) {
		bibliotecaRepository.delete(livro);
	}

	@Override
	public Livro buscarLivro(Integer id) {
		return bibliotecaRepository.findById(id).get();
	}

	@Override
	public List<Livro> listarTodosOrdenarEditora() {
		return bibliotecaRepository.findAllByOrderByEditoraAsc();
	}

	@Override
	public Livro buscarLivroPorNome(String nome) {
		return bibliotecaRepository.findLivroByNome(nome);
	}

}
