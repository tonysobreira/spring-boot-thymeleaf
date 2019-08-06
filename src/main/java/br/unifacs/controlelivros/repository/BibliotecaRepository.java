package br.unifacs.controlelivros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unifacs.controlelivros.model.Livro;

@Repository("bibliotecaRepository")
public interface BibliotecaRepository extends JpaRepository<Livro, Integer> {

	List<Livro> findAllByOrderByEditoraAsc();
	Livro findLivroByNome(String nome);

}