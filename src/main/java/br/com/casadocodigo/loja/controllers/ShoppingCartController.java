package br.com.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.PaymentData;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;
import br.com.casadocodigo.loja.models.TipoLivro;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer produtoId, @RequestParam TipoLivro tipoLivro) {
		ShoppingItem item = createItem(produtoId, tipoLivro);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String itens() {
		logger.info("Acessando o carrinho");
		return "carrinhos/items";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public Callable<String> checkout() {
		return () -> {
			BigDecimal total = shoppingCart.getTotal();
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			try {
				String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				System.out.println(response);
				return "redirect:/produtos";
			} catch (HttpClientErrorException exception) {
				System.out.println("Ocorreu um erro ao criar o Pagamento: " + exception.getMessage());
				return "redirect:/shopping";
			}
		};
	}

	private ShoppingItem createItem(Integer produtoId, TipoLivro tipoLivro) {
		Produto produto = produtoDAO.find(produtoId);
		ShoppingItem item = new ShoppingItem(produto, tipoLivro);
		return item;
	}

}
