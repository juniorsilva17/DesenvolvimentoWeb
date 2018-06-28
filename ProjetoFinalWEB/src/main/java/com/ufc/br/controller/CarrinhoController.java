package com.ufc.br.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.ComprasCarrinho;
import com.ufc.br.model.Produto;
import com.ufc.br.model.ProdutoCarrinho;
import com.ufc.br.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)

public class CarrinhoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	public ComprasCarrinho carrinho;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("carrinho");
		Collection<ProdutoCarrinho> itens = carrinho.getProdutos();
		for (ProdutoCarrinho carrinhoItem : itens) {
			int qtd = carrinho.getQuantidade(carrinhoItem);
			double total = carrinho.getTotal(carrinhoItem);
			modelAndView.addObject("qtd", qtd);
			modelAndView.addObject("total", total);

		}
		modelAndView.addObject("itens", itens);
		return modelAndView;
	}
	
	private ProdutoCarrinho criaItem(Long produtoId) {
		Produto produto = produtoService.find(produtoId);
		ProdutoCarrinho carrinhoItem = new ProdutoCarrinho(produto);
		return carrinhoItem;
	}	
	@RequestMapping("/add")
	public ModelAndView add(Long produtoId) {
		ProdutoCarrinho carrinhoItem = criaItem(produtoId);
		carrinho.add(carrinhoItem);
		return new ModelAndView("redirect:/carrinho");
	}

	@RequestMapping("/remover")
	public ModelAndView remover(Long produtoId) {
		carrinho.remover(produtoId);
		return new ModelAndView("redirect:/carrinho");
	}
}
