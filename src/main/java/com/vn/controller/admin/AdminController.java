package com.vn.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entity.Product;
import com.vn.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductService productService;
	
	@GetMapping(value = {"/home", "/index", "/", ""})
	public String dashBoard(Model model,@RequestParam("p") Optional<Integer> p) {
		
	Pageable pageable=PageRequest.of(p.orElse(0), 8,Sort.by("id").descending());
	Page<Product> page=productService.findAll(pageable);
	
	//List<Product> list= page.getContent();
	model.addAttribute("ListProduct",page);

		return "homePageAdmin";
	}

}
