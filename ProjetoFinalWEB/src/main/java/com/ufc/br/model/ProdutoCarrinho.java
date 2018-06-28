package com.ufc.br.model;

import java.math.BigDecimal;

public class ProdutoCarrinho {

	private Produto produto;

	public ProdutoCarrinho(Produto produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return produto.getPreco();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Double getTotal(int quantidade) {
		return ((this.produto.getPreco()) * quantidade);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoCarrinho other = (ProdutoCarrinho) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

}
