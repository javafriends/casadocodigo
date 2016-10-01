package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoLivro;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//		dataBinder.addValidators(new ProdutoValidator());
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable(value="listProdutos")
	public ModelAndView list() {
		System.out.println("Entrei no Controller para listar");
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
	@CacheEvict(value="listProdutos", allEntries=true)
	@RequestMapping(method=RequestMethod.POST, name="saveProduto")
	public ModelAndView save(@Valid Produto produto, BindingResult result, 
			RedirectAttributes redirectAttributes, MultipartFile sumario) {
		if (result.hasErrors()) {
			return form(produto);
		}
		
		String sumarioPath = fileSaver.salvar("upload-sumarios", sumario);
		produto.setSumarioPath(sumarioPath);
		
		produtoDAO.save(produto);
		redirectAttributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public ModelAndView show(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("produtos/show");
		mav.addObject("produto", produtoDAO.find(id)) ;
		return mav;
	}

}
