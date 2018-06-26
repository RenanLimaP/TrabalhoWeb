package br.com.fastshop.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fastshop.model.CarrinhoCompras;
import br.com.fastshop.model.CarrinhoItem;
import br.com.fastshop.model.Produto;
import br.com.fastshop.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	public CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add (Long produtoId, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId);
		carrinho.add(carrinhoItem);
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("carrinho/itens");
		Map<CarrinhoItem, Integer> itens = carrinho.getItens();
//		for (CarrinhoItem carrinhoItem : itens) {
//			int qtd = carrinho.getQuantidade(carrinhoItem);
//			BigDecimal total = carrinhoItem.getTotal(qtd);
//			
//			modelAndView.addObject("qtd", qtd);
//			modelAndView.addObject("total", total);
//
//		}
		
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
