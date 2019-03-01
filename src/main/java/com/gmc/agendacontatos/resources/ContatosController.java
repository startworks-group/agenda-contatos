package com.gmc.agendacontatos.resources;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gmc.agendacontatos.domain.Contato;
import com.gmc.agendacontatos.repository.ContatosRepository;

@Controller
@RequestMapping("/contatos")
public class ContatosController {
	
	@Autowired
	private ContatosRepository contatosRepository;
	
	@GetMapping
	public ModelAndView listar() {
		List<Contato> lista = contatosRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("contatos");
		modelAndView.addObject("listadecontatos", lista);
		
		return modelAndView;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastrar(Contato contato) {
		
		ModelAndView modelAndView = new ModelAndView("cadastrarContato");
		
		modelAndView.addObject(contato);
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView buscar(@PathVariable("id") long id) {
		
		Contato contato=null;
		try {
			contato = contatosRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return new ModelAndView("contatoNotFound");
		}
		
		ModelAndView modelAndView = new ModelAndView("contato");
		modelAndView.addObject("contato", contato);
		
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Contato contato, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return cadastrar(contato);
		}
		
		contatosRepository.save(contato);
		return new ModelAndView("redirect:/contatos");
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable("id") long id) {
		contatosRepository.deleteById(id);
	}
}
