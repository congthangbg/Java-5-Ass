package com.vn.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entity.Product;
import com.vn.service.ProductService;


@Controller
@RequestMapping("product")
public class ProductController2 {
	
	@Autowired
	ProductService productService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	@GetMapping("views")
	public String viewProduct(Model model,@RequestParam("field") Optional<String> filed) {
		
//		Pageable sortedByPriceDesc = 
//		  PageRequest.of(0, 3, Sort.by("price").descending());
		Sort sort=Sort.by(Direction.DESC,filed.orElse("price"));
		List<Product> list=(List<Product>) productService.findAll(sort);
		model.addAttribute("ListProduct", list);
		return "view-products";
	}
	
	@GetMapping("views/page")
	public String paginate(Model model,@RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> filed) {

		Pageable pageable=PageRequest.of(p.orElse(0), 5,Sort.by(Direction.DESC,filed.orElse("price")));
		Page<Product> page=productService.findAll(pageable);
		model.addAttribute("ListProduct", page);
		return "view-products";
	}
}
