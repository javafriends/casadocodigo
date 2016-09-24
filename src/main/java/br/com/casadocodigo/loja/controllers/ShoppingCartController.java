package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;
import br.com.casadocodigo.loja.models.TipoLivro;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(Integer produtoId, @RequestParam TipoLivro tipoLivro) {
		ShoppingItem item = createItem(produtoId, tipoLivro);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}

	private ShoppingItem createItem(Integer produtoId, TipoLivro tipoLivro) {
		Produto produto = produtoDAO.find(produtoId);
		ShoppingItem item = new ShoppingItem(produto, tipoLivro);
		return item;
	}

}
