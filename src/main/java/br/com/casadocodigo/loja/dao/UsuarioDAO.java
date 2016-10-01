package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String jpql = "select u from Usuario u where u.login = :login";
		List<Usuario> usuarios = em.createQuery(jpql, Usuario.class).setParameter("login", username).getResultList();
		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("O usuario " + username + " não existe");
		}
		return usuarios.get(0);
	}

}
