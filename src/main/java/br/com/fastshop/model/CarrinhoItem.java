package br.com.fastshop.model;

import java.math.BigDecimal;

public class CarrinhoItem {

	public Produto produto;
	
	
	public CarrinhoItem(Produto produto) {
		this.produto = produto;
	}
//
//	public BigDecimal getTotal(int quantidade) {
//		return this.produto.getPreco().multiply(new BigDecimal(quantidade));
//	}
//	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		CarrinhoItem other = (CarrinhoItem) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	

	
	
	
	
	
}
