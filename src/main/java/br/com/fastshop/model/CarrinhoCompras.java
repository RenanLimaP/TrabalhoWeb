package br.com.fastshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.assertj.core.internal.bytebuddy.description.type.TypeDescription;
import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
//	public List<CarrinhoItem> itens = new ArrayList<CarrinhoItem>();
//	
//	public List<CarrinhoItem> getItens() {
//		return itens;
//	}
//	
//	public void setItens(List<CarrinhoItem> itens) {
//		this.itens = itens;
//	}
//	
//	public void add(CarrinhoItem item) {
//		this.itens.add(item);
//	}
	
	
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

	
	public Map<CarrinhoItem,Integer> getItens() {
		return itens;
	}

	public void setItens(Map<CarrinhoItem, Integer> itens) {
		this.itens = itens;
	}

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public Integer getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		return this.itens.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;	
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remover(Long produtoId) {
	    Produto produto = new Produto();
	    produto.setId(produtoId);
	    itens.remove(new CarrinhoItem(produto));
	}
	
		
}
