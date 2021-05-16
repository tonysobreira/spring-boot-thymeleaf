package br.unifacs.controlelivros.service.impl;

import java.util.List;
import java.util.Optional;

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
		Optional<Livro> optionalLivro = bibliotecaRepository.findById(id);

		if (optionalLivro.isPresent()) {
			return optionalLivro.get();
		}

		return null;
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
