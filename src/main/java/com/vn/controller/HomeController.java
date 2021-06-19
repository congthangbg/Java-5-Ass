package com.vn.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entity.Category;
import com.vn.entity.Product;
import com.vn.repository.ProductRepository;
import com.vn.service.CategoryService;
import com.vn.service.ProductService;

@Controller
@RequestMapping("/user")
public class HomeController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	HttpServletRequest request;
	
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
		
		List<Category> listCate=categoryService.findAll();
		model.addAttribute("listCate", listCate);
		
		model.addAttribute("ListProduct",page);
		return "homePage";
	}
	
	@GetMapping("/products/{id}")
	public String detailPage(Model model){
		model.addAttribute("user", "Product Page");
		return "detailProductPage";
	} 
	
//	@GetMapping("/login")
//	public String login(Model model){
//		return "/auth/login";
//	} 
	
	
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
	
	@GetMapping("/cateId")
	public String getCategory(Model model) {
		String id=request.getParameter("category");
		String keySearch=request.getParameter("productName");
		if(id.equals("999")) {
			return "redirect:/user/home";
		}
		
		System.out.println(keySearch);
		List<Category> listcate= categoryService.findAll();
		model.addAttribute("listCate",listcate);
	
		List<Product> list=productService.filterProductByCateId(Integer.parseInt(id));
		
		Page<Product> page=new PageImpl<Product>(list);
		
		model.addAttribute("ListProduct",page);
		
		return "homePage";
	}

}
