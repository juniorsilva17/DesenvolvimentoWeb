package com.ufc.br.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ufc.br.model.Produto;
import com.ufc.br.repository.ProdutoRepository;
import com.ufc.br.util.AulaFileUtils;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@PersistenceContext
	private EntityManager manager;

	public void adicionarProduto(Produto produto, MultipartFile imagem) {
		String caminho = "images/" + produto.getNome() + ".jpg";
		AulaFileUtils.salvarImagem(caminho, imagem);

		produtoRepository.save(produto);
	}

	public List<Produto> retornarTodosOsProdutos() {

		return produtoRepository.findAll();
	}

	public Produto buscarPorId(Long id) {
		return produtoRepository.getOne(id);
	}

	public Produto find(Long id) {
		return manager.createQuery("select distinct(p) from Produto p where p.id = :id", Produto.class)
				.setParameter("id", id).getSingleResult();
	}

	public void removerProduto(Long id) {
		produtoRepository.deleteById(id);

	}

}
