package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoLivro;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtoDAO.list());
		return modelAndView;
	}
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("produtos/form");
		mav.addObject("tipos", TipoLivro.values());
		return mav;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public String save(Produto produto) {
		System.out.println("Cadastrando produto: " + produto);
		produtoDAO.save(produto);
		return "redirect:produtos";
	}

}
