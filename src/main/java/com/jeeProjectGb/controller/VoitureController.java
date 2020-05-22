package com.jeeProjectGb.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import com.jeeProjectGb.entities.*;
import com.jeeProjectGb.repositories.*;






@Controller
@RequestMapping("/voiture/")
public class VoitureController {
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	private final VoitureRepository voitureRepository;
	private final CategoryRepository categoryRepository;
	@Autowired
	  public VoitureController( VoitureRepository voitureRepository, CategoryRepository categoryRepository) {
	      this.voitureRepository = voitureRepository;
	      this.categoryRepository = categoryRepository;
	  }
	public List<Voiture> findByMarque(String marque){
		return voitureRepository.findByMarque("%"+marque+"%");
		
	}
	
	@GetMapping("list")
	  public String listProviders(Model model) {
	  	//model.addAttribute("articles", null);
	      model.addAttribute("voitures", voitureRepository.findAll());
	      return "voiture/listVoitures";
	  }
	@GetMapping("listMarque")
	  public String listVoitures() {
	  	//model.addAttribute("articles", null);
		
	    
	      return "voiture/listVoituresMarque";
	  }
	
	@PostMapping("listMarque")
	  public String recherche(Model model) {
	  	//model.addAttribute("articles", null);
		
	    
	      return "voiture/listVoituresMarque";
	  }
	 @GetMapping("add")
	  public String showAddVoitureForm(Voiture voiture, Model model) {
	  	
	  	model.addAttribute("categories", categoryRepository.findAll());
	  	//model.addAttribute("article", new Article());
	      return "voiture/addVoiture";
	  }
	
	@PostMapping("add")
	  //@ResponseBody
	  public String addVoiture( Voiture voiture, BindingResult result, @RequestParam(name = "categorieId", required = false) Long v,
	  		@RequestParam("files") MultipartFile[] files)  {
	  	
	  	Categorie categorie = categoryRepository.findById(v)
	              .orElseThrow(()-> new IllegalArgumentException("Invalid category Id:" + v));
	  	voiture.setCategorie(categorie);
	  	
	  /// part upload
	  	
		StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		
		fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 voiture.setPicture(fileName.toString());
		 /*
		  * format date 
		  */
		
	     voitureRepository.save(voiture);
		 return "redirect:list";
		
		//return article.getLabel() + " " +article.getPrice() + " " + p.toString();
	
	}
	
	
	@GetMapping("delete/{id}")
	public String deleteVoiture(@PathVariable("id") long id, Model model) {
	    Voiture voiture = voitureRepository.findById(id);  
	    voitureRepository.delete(voiture);
	    model.addAttribute("voitures", voitureRepository.findAll());
	    return "voiture/listVoitures";
	}

	
	@GetMapping("edit/{id}")
	public String showArticleFormToUpdate(@PathVariable("id") long id, Model model) {
		Voiture voiture = voitureRepository.findById(id);
	        
		
	    model.addAttribute("voiture", voiture);
	    model.addAttribute("categories", categoryRepository.findAll());
	    model.addAttribute("idCategorie", voiture.getCategorie().getId());
	    
	    return "voiture/updateVoiture";
	}
	
	
	@PostMapping("edit/{id}")
	public String updateArticle(@PathVariable("id") long id,  Voiture voiture, BindingResult result,
	    Model model,@RequestParam("files") MultipartFile[] files, @RequestParam(name = "categorieId", required = false) Long p) {
	    if (result.hasErrors()) {
	    	voiture.setId(id);
	        return "voiture/updateVoiture";
	    }
	    
	    Categorie categorie = categoryRepository.findById(p)
	            .orElseThrow(()-> new IllegalArgumentException("Invalid categorie Id:" + p));
		voiture.setCategorie(categorie);
		
		
		
		StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		
		fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 voiture.setPicture(fileName.toString());
		
		
	    voitureRepository.save(voiture);
	    model.addAttribute("voitures", voitureRepository.findAll());
	    return "voiture/listVoitures";
	}

}
