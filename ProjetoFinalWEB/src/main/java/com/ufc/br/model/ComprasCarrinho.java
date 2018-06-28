package com.ufc.br.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ComprasCarrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<ProdutoCarrinho, Integer> produtos = new LinkedHashMap<>();

	public Collection<ProdutoCarrinho> getProdutos() {
		return produtos.keySet();
	}

	public void setProdutos(Map<ProdutoCarrinho, Integer> produtos) {
		this.produtos = produtos;
	}

	public void add(ProdutoCarrinho produto) {
		produtos.put(produto, getQuantidade(produto) + 1);
	}

	public Integer getQuantidade(ProdutoCarrinho item) {
		if(!produtos.containsKey(item)) {
			produtos.put(item, 0);
		}
		return produtos.get(item);
	}
	
	public int getQuantidade() {
		return this.produtos.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	public Double getTotal(ProdutoCarrinho produto) {
		return produto.getTotal(getQuantidade(produto));
	}
	
	public Double getTotal() {
		double total = 0;	
		for (ProdutoCarrinho produto : produtos.keySet()) {
			total += getTotal(produto);
		}
		return total;
	}


	public void remover(Long produtoID) {
		Produto produto = new Produto();
		produto.setId(produtoID);
		produtos.remove(new ProdutoCarrinho(produto));
	}
}
