package com.vn.controller.admin;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vn.entity.Category;
import com.vn.entity.Product;
import com.vn.service.CategoryService;
import com.vn.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	HttpServletRequest request;

	@GetMapping(value = { "/home", "/index", "/", "" })
	public String dashBoard(Model model, @RequestParam("p") Optional<Integer> p) {

		Pageable pageable = PageRequest.of(p.orElse(0), 8, Sort.by("id").descending());
		Page<Product> page = productService.findAll(pageable);

		List<Category> list = categoryService.findAll();

		model.addAttribute("listCate", list);
		model.addAttribute("ListProduct", page);

		return "homePageAdmin";
	}

	@GetMapping("/cateId")
	public String getCategory(Model model) {
		String id = request.getParameter("category");
		// lọc all (All = 999)
		if (id.equals("999")) {
			return "redirect:/admin/home";
		}

		List<Category> listcate = categoryService.findAll();
		model.addAttribute("listCate", listcate);

		List<Product> list = productService.filterProductByCateId(Integer.parseInt(id));

		Page<Product> page = new PageImpl<Product>(list);

		model.addAttribute("ListProduct", page);

		return "homePageAdmin";
	}

	@PostMapping("/search")
	public String keySeach(Model model, @RequestParam("productName") String productName) {
		List<Product> product = productService.findByNameContaining(productName);
		Page<Product> page = new PageImpl<Product>(product);

		List<Category> listcate = categoryService.findAll();
		model.addAttribute("listCate", listcate);
		
		model.addAttribute("ListProduct", page);

		return "homePageAdmin";
	}

}
