package com.vn.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entity.Product;
import com.vn.repository.ProductRepository;
import com.vn.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public String getAll(@RequestParam(value="name", required = false) String name, Model model){
		model.addAttribute("list", productService.findByNameContaining(name));
		return "productListPage";
	}
	
	@GetMapping(value = {"/home", "/index", "/", ""})
	public String homePage(Model model,@RequestParam("p") Optional<Integer> p){
		Pageable pageable=PageRequest.of(p.orElse(0), 8,Sort.by("id").descending());
		Page<Product> page=productService.findAll(pageable);
		
		//List<Product> list= page.getContent();
		
		model.addAttribute("ListProduct",page);
		return "homePage";
	}
	
	@GetMapping("/products/{id}")
	public String detailPage(Model model){
		model.addAttribute("user", "Product Page");
		return "detailProductPage";
	} 
	
	@GetMapping("/login")
	public String login(Model model){
		return "login";
	} 
	
	@GetMapping("/register")
	public String register(Model model){
		return "register";
	} 
	
	@GetMapping("/forgot-password")
	public String forgotpassword(Model model){
		return "forgot-password";
	} 
	
	@GetMapping("/404")
	public String page404(Model model){
		return "404";
	} 

}
