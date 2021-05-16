package br.unifacs.controlelivros.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.unifacs.controlelivros.dto.LivroDTO;
import br.unifacs.controlelivros.service.BibliotecaService;

@Controller("bibliotecaController")
public class BibliotecaController {

	@Autowired
	private BibliotecaService bibliotecaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("index");
		modelAndView.addObject("titulo", "Controle de Livros");

		return modelAndView;
	}

	@RequestMapping(value = "/biblioteca", method = RequestMethod.GET)
	public ModelAndView biblioteca() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("biblioteca");
		modelAndView.addObject("titulo", "Biblioteca");
		modelAndView.addObject("operacao", "Adicionar Livro ");
		modelAndView.addObject("adicionarLivro", true);
		modelAndView.addObject("livros", bibliotecaService.listarTodos());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());
		modelAndView.addObject("livroDTO", new LivroDTO());

		return modelAndView;
	}

	@RequestMapping(value = "/adicionarEditarLivro", method = RequestMethod.POST)
	public ModelAndView adicionarEditarLivro(@Valid LivroDTO livroDTO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("titulo", "Biblioteca");
		Boolean isAdicionar = livroDTO.getId() == null ? true : false;

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("biblioteca");
			modelAndView.addObject("livros", bibliotecaService.listarTodos());
			modelAndView.addObject("livrosCadastrados",
					"Livros cadastrados: " + bibliotecaService.listarTodos().size());
			
			if (isAdicionar) {
				modelAndView.addObject("operacao", "Adicionar Livro ");
				modelAndView.addObject("adicionarLivro", true);
			} else {
				modelAndView.addObject("operacao", "Editar Livro ");
				modelAndView.addObject("adicionarLivro", false);
			}

			return modelAndView;
		}

		modelAndView.addObject("operacao", "Adicionar Livro ");
		modelAndView.addObject("adicionarLivro", true);

		if (isAdicionar) {

			LivroDTO existeLivro = bibliotecaService.buscarLivroPorNome(livroDTO.getNome());

			if (existeLivro != null) {
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("mensagem", "Já existe um livro com esse nome!");
			} else {
				bibliotecaService.adicionarLivro(livroDTO);
				modelAndView.addObject("mensagem", "Livro adicionado com sucesso!");
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("livroDTO", new LivroDTO());
			}

		} else {

			LivroDTO existeLivro = bibliotecaService.buscarLivroPorNome(livroDTO.getNome());

			if (existeLivro != null && !existeLivro.getId().equals(livroDTO.getId())) {
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("mensagem", "Já existe um livro com esse nome!");
				modelAndView.addObject("operacao", "Editar Livro ");
				modelAndView.addObject("adicionarLivro", false);
			} else {
				bibliotecaService.adicionarLivro(livroDTO);
				modelAndView.addObject("mensagem", "Livro editado com sucesso!");
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("livroDTO", new LivroDTO());
			}

		}

		modelAndView.addObject("livros", bibliotecaService.listarTodos());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());
		return modelAndView;
	}

	@GetMapping(value = "/removerLivro/{id}")
	public ModelAndView removerLivro(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("titulo", "Biblioteca");
		modelAndView.addObject("operacao", "Adicionar Livro ");
		modelAndView.addObject("adicionarLivro", true);

		LivroDTO livro = null;

		try {
			livro = bibliotecaService.buscarLivro(id);
		} catch (NoSuchElementException e) {
			modelAndView.addObject("mensagem", "Livro não encontrado!");
		}

		if (livro != null) {
			bibliotecaService.removerLivro(livro);
			modelAndView.addObject("mensagem", "Livro removido com sucesso!");
		}

		modelAndView.setViewName("biblioteca");
		modelAndView.addObject("livroDTO", new LivroDTO());

		modelAndView.addObject("livros", bibliotecaService.listarTodos());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());

		return modelAndView;
	}

	@GetMapping(value = "/editarLivro/{id}")
	public ModelAndView editarLivro(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("titulo", "Biblioteca");
		modelAndView.addObject("operacao", "Editar Livro ");
		modelAndView.addObject("adicionarLivro", false);

		LivroDTO livro = null;

		try {
			livro = bibliotecaService.buscarLivro(id);
		} catch (NoSuchElementException e) {
			modelAndView.addObject("mensagem", "Livro não encontrado!");
		}

		if (livro != null) {
			modelAndView.addObject("livroDTO", livro);
		} else {
			modelAndView.addObject("livroDTO", new LivroDTO());
		}

		modelAndView.setViewName("biblioteca");
		modelAndView.addObject("livros", bibliotecaService.listarTodos());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());

		return modelAndView;
	}

	@GetMapping(value = "/listarPorEditora")
	public ModelAndView listarPorEditora() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("titulo", "Biblioteca");
		modelAndView.addObject("operacao", "Adicionar Livro ");
		modelAndView.addObject("adicionarLivro", true);

		modelAndView.setViewName("biblioteca");
		modelAndView.addObject("livroDTO", new LivroDTO());

		modelAndView.addObject("livros", bibliotecaService.listarTodosOrdenarEditora());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());

		return modelAndView;
	}

}
