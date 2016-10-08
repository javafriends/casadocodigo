package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoLivro;

@Repository
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> list() {
		System.out.println("Entrei no DAO para listar");
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos", Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos where p.id = :id", Produto.class).
				setParameter("id", id).getSingleResult();
	}

	public BigDecimal sumPricesPerType(TipoLivro tipoLivro) {
		TypedQuery<BigDecimal>	query	=	
			manager.createQuery("select	sum(preco.valor)	from	Produto	p	join	p.precos	preco where	preco.tipoLivro	=	:tipoLivro",	BigDecimal.class);
			query.setParameter("tipoLivro",	tipoLivro);
			return	query.getSingleResult();
	}
	
}
