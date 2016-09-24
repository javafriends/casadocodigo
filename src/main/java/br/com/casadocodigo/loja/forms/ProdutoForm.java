package br.com.casadocodigo.loja.forms;

import org.springframework.web.multipart.MultipartFile;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoForm {
	
	private Produto produto;
	private MultipartFile sumario;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public MultipartFile getSumario() {
		return sumario;
	}
	public void setSumario(MultipartFile sumario) {
		this.sumario = sumario;
	}
	
	

}
