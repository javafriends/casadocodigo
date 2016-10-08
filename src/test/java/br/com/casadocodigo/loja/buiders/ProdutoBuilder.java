package br.com.casadocodigo.loja.buiders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoLivro;

public class ProdutoBuilder {

	private List<Produto> produtos = new ArrayList<Produto>();

	private ProdutoBuilder(Produto product) {
		produtos.add(product);
	}

	public static ProdutoBuilder newProduto(TipoLivro livroType, BigDecimal value) {
		Produto livro = create("Book	1", livroType, value);
		return new ProdutoBuilder(livro);
	}

	public static ProdutoBuilder newProduto() {
		Produto livro = create("Book	1", TipoLivro.COMBO, BigDecimal.TEN);
		return new ProdutoBuilder(livro);
	}

	private static Produto create(String livroName, TipoLivro livroType, BigDecimal valor) {
		Produto livro = new Produto();
		livro.setTitulo(livroName);
		livro.setDataLancamento(Calendar.getInstance());
		livro.setNumeroPaginas(150);
		livro.setDescricao("great	livro	about	testing");
		Preco preco = new Preco();
		preco.setTipoLivro(livroType);
		preco.setValor(valor);
		livro.getPrecos().add(preco);
		return livro;
	}

	public ProdutoBuilder more(int number) {
		Produto base = produtos.get(0);
		Preco preco = base.getPrecos().get(0);
		for (int i = 0; i < number; i++) {
			produtos.add(create("Book	" + i, preco.getTipoLivro(), preco.getValor()));
		}
		return this;
	}

	public Produto buildOne() {
		return produtos.get(0);
	}

	public List<Produto> buildAll() {
		return produtos;
	}

}
