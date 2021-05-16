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

import br.unifacs.controlelivros.model.Livro;
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
		modelAndView.addObject("livros", bibliotecaService.listarTodos());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());
		modelAndView.addObject("livro", new Livro());

		return modelAndView;
	}

	@RequestMapping(value = "/adicionarEditarLivro", method = RequestMethod.POST)
	public ModelAndView adicionarEditarLivro(@Valid Livro livro, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("titulo", "Biblioteca");
		Boolean isAdicionar = livro.getId() == null ? true : false;
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("biblioteca");
			modelAndView.addObject("livros", bibliotecaService.listarTodos());
			modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());
			
			if (isAdicionar) {
				modelAndView.addObject("operacao", "Adicionar Livro ");
			} else {
				modelAndView.addObject("operacao", "Editar Livro ");
			}
			
			return modelAndView;
		}
		
		modelAndView.addObject("operacao", "Adicionar Livro ");
		
		if (isAdicionar) {
			
			Livro existeLivro = bibliotecaService.buscarLivroPorNome(livro.getNome());
			
			if (existeLivro != null) {
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("mensagem", "Já existe um livro com esse nome!");
			} else {
				bibliotecaService.adicionarLivro(livro);
				modelAndView.addObject("mensagem", "Livro adicionado com sucesso!");
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("livro", new Livro());
			}
			
		} else {
			
			Livro existeLivro = bibliotecaService.buscarLivroPorNome(livro.getNome());
			
			if (existeLivro != null && !existeLivro.getId().equals(livro.getId())) {
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("mensagem", "Já existe um livro com esse nome!");
				modelAndView.addObject("operacao", "Editar Livro ");
			} else {
				bibliotecaService.adicionarLivro(livro);
				modelAndView.addObject("mensagem", "Livro editado com sucesso!");
				modelAndView.setViewName("biblioteca");
				modelAndView.addObject("livro", new Livro());
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

		Livro livro = null;

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
		modelAndView.addObject("livro", new Livro());

		modelAndView.addObject("livros", bibliotecaService.listarTodos());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());

		return modelAndView;
	}

	@GetMapping(value = "/editarLivro/{id}")
	public ModelAndView editarLivro(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("titulo", "Biblioteca");
		modelAndView.addObject("operacao", "Editar Livro ");

		Livro livro = null;

		try {
			livro = bibliotecaService.buscarLivro(id);
		} catch (NoSuchElementException e) {
			modelAndView.addObject("mensagem", "Livro não encontrado!");
		}
		
		if (livro != null) {
			modelAndView.addObject("livro", livro);
		} else {
			modelAndView.addObject("livro", new Livro());
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

		modelAndView.setViewName("biblioteca");
		modelAndView.addObject("livro", new Livro());

		modelAndView.addObject("livros", bibliotecaService.listarTodosOrdenarEditora());
		modelAndView.addObject("livrosCadastrados", "Livros cadastrados: " + bibliotecaService.listarTodos().size());

		return modelAndView;
	}

}
