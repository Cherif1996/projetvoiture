package com.jeeProjectGb.controller;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.jeeProjectGb.entities.Categorie;
import com.jeeProjectGb.repositories.CategoryRepository;






@Controller
@RequestMapping("/categorie/")
public class CategorieController {
	private final CategoryRepository categoryRepository;
	@Autowired
	public CategorieController(CategoryRepository categoryRepository ) {
		this.categoryRepository=categoryRepository;
	}
	 @GetMapping("list")
	    public String listProviders(Model model) {
	        model.addAttribute("categories", categoryRepository.findAll());
	        return "categorie/listCategories";
	    }
	
	 @GetMapping("add")
	    public String showAddCategorieForm(Categorie categorie) {
	        return "categorie/addCategorie";
	    }
	
	 @PostMapping("add")
	    public String addCategorie( Categorie categorie, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "categorie/addCategorie";
	        }
	        categoryRepository.save(categorie);
	        return "redirect:list";
	    }
	 
	 @GetMapping("delete/{id}")
	    public String deleteProvider(@PathVariable("id") long id, Model model) {
	        Categorie categorie = categoryRepository.findById(id);
	        categoryRepository.delete(categorie);
	        model.addAttribute("categories", categoryRepository.findAll());
	        return "categorie/listCategories";
	 }
	 
	 

	 @GetMapping("edit/{id}")
	    public String showCategorieFormToUpdate(@PathVariable("id") long id, Model model) {
	        Categorie categorie = categoryRepository.findById(id);
	        model.addAttribute("categorie", categorie);
	        return "categorie/updateCategorie";
	    }
	    @PostMapping("update/{id}")
	    public String updateCategorie(@PathVariable("id") long id, Categorie categorie, BindingResult result,
	        Model model) {
	    	if (result.hasErrors()) {
	            categorie.setId(id);
	            return "categorie/updateCategorie";
	        }
	    	categoryRepository.save(categorie);
	        model.addAttribute("categories", categoryRepository.findAll());
	        return "categorie/listCategories";
	    }

	 
	 
}
