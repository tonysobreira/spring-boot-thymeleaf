package br.unifacs.controlelivros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	@NotEmpty(message = "*digite o nome")
	private String nome;

	@Column(name = "editora")
	@NotEmpty(message = "*digite a editora")
	private String editora;

	@Column(name = "edicao")
	@NotNull(message = "*digite a edição")
	private Integer edicao;

	@Column(name = "area")
	@NotEmpty(message = "*digite a area")
	private String area;

	public Livro() {
	}

	public Livro(Integer id, @NotEmpty(message = "*digite o nome") String nome,
			@NotEmpty(message = "*digite a editora") String editora,
			@NotNull(message = "*digite a edicao") Integer edicao, @NotEmpty(message = "*digite a area") String area) {
		this.id = id;
		this.nome = nome;
		this.editora = editora;
		this.edicao = edicao;
		this.area = area;
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

}