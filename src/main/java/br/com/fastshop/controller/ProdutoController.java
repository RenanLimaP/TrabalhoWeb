package br.com.fastshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fastshop.model.Produto;
import br.com.fastshop.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/formulario")
	public ModelAndView formularioProduto() {
		ModelAndView mv = new ModelAndView("produto/formularioProduto");
		mv.addObject("produto", new Produto());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarProduto(Produto produto,@RequestParam(value= "imagem") MultipartFile imagem, RedirectAttributes redirectAttributes) {
		produtoService.addProduto(produto,imagem);
		ModelAndView mv = new ModelAndView("redirect:/produto/listar");
		redirectAttributes.addFlashAttribute("mensagem","Produto cadastrado com Sucesso!");
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listarProdutos() {
		List<Produto> produtos = produtoService.getProduto();
		ModelAndView mv = new ModelAndView("produto/listagemProduto");
		mv.addObject("produtos", produtos);
		return mv; 
	}
	
	@RequestMapping("{id}")
	public ModelAndView atualizarProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		Produto produto = produtoService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("produto/formularioProduto");
		mv.addObject("produto", produto);
		redirectAttributes.addFlashAttribute("mensagem","Produto atualizado com Sucesso!");
		return mv;
	}
	
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluirProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		produtoService.removerProduto(id);
		ModelAndView mv = new ModelAndView("redirect:/produto/listar");
		redirectAttributes.addFlashAttribute("mensagem","Produto excluído com Sucesso!");
		return mv;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("produto/detalhe");
		Produto produto = produtoService.buscarPorId(id);
		modelAndView.addObject("produto",produto);
		return modelAndView;
	}
}
