package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.buiders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoLivro;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes	=	{ProdutoDAO.class,
				JPAConfiguration.class,DataSourceConfigurationTest.class	})
@ActiveProfiles("test")
public class ProdutoDAOTest {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {
		List<Produto> printedBooks = ProdutoBuilder.newProduto(TipoLivro.IMPRESSO, BigDecimal.TEN).more(4).buildAll();
		printedBooks.stream().forEach(produtoDAO::save);
		List<Produto> ebooks = ProdutoBuilder.newProduto(TipoLivro.EBOOK, BigDecimal.TEN).more(4).buildAll();
		ebooks.stream().forEach(produtoDAO::save);
		BigDecimal value = produtoDAO.sumPricesPerType(TipoLivro.IMPRESSO);
		Assert.assertEquals(new BigDecimal(50).setScale(2), value);
	}

}
