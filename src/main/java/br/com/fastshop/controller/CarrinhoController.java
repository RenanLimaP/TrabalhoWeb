package br.com.fastshop.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.fastshop.model.CarrinhoCompras;
import br.com.fastshop.model.CarrinhoItem;
import br.com.fastshop.model.Produto;
import br.com.fastshop.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
//@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	public CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add (Long produtoId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(3L);
		List<CarrinhoItem> itens = new ArrayList<>();
		itens.add(carrinhoItem);
		carrinho.setItens(itens);
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("carrinho/itens");
		List<CarrinhoItem> itens = carrinho.getItens();
		modelAndView.addObject("itens", itens);
		return modelAndView;
	}
	
	private CarrinhoItem criaItem(Long produtoId) {
		Produto produto = produtoService.buscarPorId(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto);
		return carrinhoItem;
	}
	
//	@RequestMapping("/remover")
//	public ModelAndView remover(Integer produtoId){
//	    carrinho.remover(produtoId);
//	    return new ModelAndView("redirect:/carrinho");
//	}
	
}
