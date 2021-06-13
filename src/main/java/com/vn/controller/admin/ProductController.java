package com.vn.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vn.entity.Category;
import com.vn.entity.Product;
import com.vn.model.CategoryDto;
import com.vn.model.ProductDto;
import com.vn.service.CategoryService;
import com.vn.service.ParamService;
import com.vn.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ParamService paramService;
	
	@GetMapping(value = {"/views/page","","/"})
	public String paginate(Model model,@RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> filed,
			@RequestParam(name="name",required = false)String name
			) {
	
		Pageable pageable=PageRequest.of(p.orElse(1), 5,Sort.by(Direction.DESC,filed.orElse("id")));
		Page<Product> page=null;
		
		
		if(StringUtils.hasText(name)) {
			List<Product> list= productService.findByNameContaining(name);
			page=new PageImpl<Product>(list);
			
			}else {
				page =productService.findAll(pageable);
				
			}
		
		model.addAttribute("ListProduct", page);
		
		return "adminProductList";
	}


	@GetMapping("/views")
	public String viewForm(Model model) {
		model.addAttribute("product",new Product());
		model.addAttribute("ACTION", "product-form");
		
		List<Category> listCate=categoryService.findAll();
		model.addAttribute("listCate", listCate);
		return "adminProductCreate";
	}
	
	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap modelMap,@PathVariable("id") Integer id, Model model) {
		
		Optional<Product> opt=productService.findById(id); 
		ProductDto dto=new ProductDto();
		if(opt.isPresent()) {
			Product entity=opt.get();
			
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			
			modelMap.addAttribute("product", dto);
			List<Category> listCate=categoryService.findAll();
			
			model.addAttribute("listCate", listCate);
			return new ModelAndView("adminProductCreate",modelMap) ;
		}
		
		modelMap.addAttribute("message", "Category is not exited!");
		return new ModelAndView("redirect:/admin/products",modelMap);
	}
	@PostMapping("/add")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("product")ProductDto dto,BindingResult result) {
			if(result.hasErrors()) {
				List<Category> listCate=categoryService.findAll();
				model.addAttribute("listCate", listCate);
				
				return new ModelAndView("adminProductCreate");
			}
			Product entity=new Product();
			BeanUtils.copyProperties(dto, entity);
			
	
			productService.save(entity);
			
			session=request.getSession();
			session.setAttribute("message", "Product is saved!");
			return new ModelAndView("redirect:/admin/products/views/page",model);
		}

	@GetMapping("delete/{id}")
	public ModelAndView delete(ModelMap model,@PathVariable("id") Integer id) {
		productService.deleteById(id);
		session=request.getSession();
		session.setAttribute("message", "Product is deleted!");
		return new ModelAndView("redirect:/admin/products",model);
	}
	
	
}
