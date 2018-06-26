package br.com.fastshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.fastshop.model.Produto;
import br.com.fastshop.repository.ProdutoRepository;
import br.com.fastshop.util.ImagemUtils;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository; 
	
	public void addProduto(Produto produto, MultipartFile imagem) {
		String caminho = "images/" + produto.getFornecedor() + "_" + produto.getNome() + ".png";
		String nome = produto.getFornecedor() + "_" + produto.getNome() + ".png";
		ImagemUtils.salvarImagem(caminho,imagem);
		produto.setImgPath(nome);
		produtoRepository.save(produto);
	}
	

	public List<Produto> getProduto() {
		
		return produtoRepository.findAll();
	}
	
	public Produto buscarPorId(Long id) {
		return produtoRepository.getOne(id);
	}

	public void removerProduto(Long id) {
		produtoRepository.deleteById(id);
		
	}
	
	
}
