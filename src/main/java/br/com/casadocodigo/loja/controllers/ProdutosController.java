package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoLivro;
import br.com.casadocodigo.loja.validators.ProdutoValidator;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(new ProdutoValidator());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtoDAO.list());
		return modelAndView;
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView mav = new ModelAndView("produtos/form");
		mav.addObject("tipos", TipoLivro.values());
		return mav;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST, name="saveProduto")
	public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return form(produto);
		}
		System.out.println("Cadastrando produto: " + produto);
		produtoDAO.save(produto);
		redirectAttributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
		return new ModelAndView("redirect:produtos");
	}

}
