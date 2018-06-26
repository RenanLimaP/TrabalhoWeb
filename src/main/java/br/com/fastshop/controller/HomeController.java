package br.com.fastshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fastshop.model.Produto;
import br.com.fastshop.service.ProdutoService;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping("/home")
	public ModelAndView paginaInicial() {
		List<Produto> produtos = produtoService.getProduto();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos",produtos);
		return modelAndView;
	}
}
