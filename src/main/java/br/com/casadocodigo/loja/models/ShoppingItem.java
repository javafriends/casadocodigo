package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingItem implements Serializable {

	private Produto produto;
	private TipoLivro tipoLivro;
	private Integer produtoId;
	
	public ShoppingItem(Produto produto, TipoLivro tipoLivro) {
		this.produto = produto;
		this.tipoLivro = tipoLivro;
		this.produtoId = produto.getId();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public TipoLivro getTipoLivro() {
		return tipoLivro;
	}
	
	public BigDecimal getPreco() {
		return produto.precoFor(tipoLivro);
	}
	
	public BigDecimal getTotal(Integer quantity) {
		return getPreco().multiply(new BigDecimal(quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tipoLivro == null) ? 0 : tipoLivro.hashCode());
		result = prime * result
				+ ((produtoId == null) ? 0 : produtoId.hashCode());
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
		ShoppingItem other = (ShoppingItem) obj;
		if (tipoLivro != other.tipoLivro)
			return false;
		if (produtoId == null) {
			if (other.produtoId != null)
				return false;
		} else if (!produtoId.equals(other.produtoId))
			return false;
		return true;
	}

}
