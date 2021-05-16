package br.unifacs.controlelivros.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.unifacs.controlelivros.model.Livro;

public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "*digite o nome")
	private String nome;

	@NotEmpty(message = "*digite a editora")
	private String editora;

	@NotNull(message = "*digite a edição")
	private Integer edicao;

	@NotEmpty(message = "*digite a área")
	private String area;

	@NotEmpty(message = "*digite o autor")
	private String autor;

	@NotEmpty(message = "*digite o isbn")
	private String isbn;

	public LivroDTO() {
	}

	public LivroDTO(Integer id, @NotEmpty(message = "*digite o nome") String nome,
			@NotEmpty(message = "*digite a editora") String editora,
			@NotNull(message = "*digite a edição") Integer edicao, @NotEmpty(message = "*digite a área") String area,
			@NotEmpty(message = "*digite o autor") String autor, @NotEmpty(message = "*digite o isbn") String isbn) {
		super();
		this.id = id;
		this.nome = nome;
		this.editora = editora;
		this.edicao = edicao;
		this.area = area;
		this.autor = autor;
		this.isbn = isbn;
	}

	public LivroDTO(Livro livro) {
		id = livro != null && livro.getId() != null ? livro.getId() : null;
		nome = livro != null && livro.getNome() != null ? livro.getNome() : null;
		editora = livro != null && livro.getEditora() != null ? livro.getEditora() : null;
		edicao = livro != null && livro.getEdicao() != null ? livro.getEdicao() : null;
		area = livro != null && livro.getArea() != null ? livro.getArea() : null;
		autor = livro != null && livro.getAutor() != null ? livro.getAutor() : null;
		isbn = livro != null && livro.getIsbn() != null ? livro.getIsbn() : null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
