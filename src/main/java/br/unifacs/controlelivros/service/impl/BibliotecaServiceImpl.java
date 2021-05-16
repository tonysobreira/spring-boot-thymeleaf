package br.unifacs.controlelivros.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifacs.controlelivros.dto.LivroDTO;
import br.unifacs.controlelivros.model.Livro;
import br.unifacs.controlelivros.repository.BibliotecaRepository;
import br.unifacs.controlelivros.service.BibliotecaService;

@Service("bibliotecaService")
public class BibliotecaServiceImpl implements BibliotecaService {

	@Autowired
	private BibliotecaRepository bibliotecaRepository;

	@Override
	public void adicionarLivro(LivroDTO livroDTO) {
		bibliotecaRepository.save(new Livro(livroDTO));
	}

	@Override
	public List<LivroDTO> listarTodos() {
		List<Livro> list = bibliotecaRepository.findAll();
		return list.stream().map(x -> new LivroDTO(x)).collect(Collectors.toList());
	}

	@Override
	public void removerLivro(LivroDTO livroDTO) {
		Optional<Livro> livroOptional = bibliotecaRepository.findById(livroDTO.getId());

		if (livroOptional.isPresent()) {
			bibliotecaRepository.delete(livroOptional.get());
		}

	}

	@Override
	public LivroDTO buscarLivro(Integer id) {
		Optional<Livro> optionalLivro = bibliotecaRepository.findById(id);

		if (optionalLivro.isPresent()) {
			return new LivroDTO(optionalLivro.get());
		}

		return null;
	}

	@Override
	public List<LivroDTO> listarTodosOrdenarEditora() {
		List<Livro> list = bibliotecaRepository.findAllByOrderByEditoraAsc();
		return list.stream().map(x -> new LivroDTO(x)).collect(Collectors.toList());
	}

	@Override
	public LivroDTO buscarLivroPorNome(String nome) {
		Livro livro = bibliotecaRepository.findLivroByNome(nome);
		return livro != null ? new LivroDTO(livro) : null;
	}

}
