package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Cliente;
import com.ufc.br.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping("/cadastrarCliente")
	public ModelAndView formularioCliente() {
		ModelAndView mv = new ModelAndView("visitante/form-cliente");
		mv.addObject("cliente", new Cliente());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarCliente(Cliente cliente) {
		clienteService.adicionarCliente(cliente);
		ModelAndView mv = new ModelAndView("visitante/form-cliente");
		mv.addObject("mensagem", "Usuário cadastrado com sucesso");

		return mv;
	}

	@RequestMapping("/logar")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("visitante/login");
		return mv;
	}
}