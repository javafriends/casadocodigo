package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

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
}
