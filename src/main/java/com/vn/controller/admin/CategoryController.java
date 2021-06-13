package com.vn.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vn.entity.Category;
import com.vn.entity.Product;
import com.vn.model.CategoryDto;
import com.vn.repository.CategoryRepository;
import com.vn.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDto());
		
		return "adminCategoryAdd";
	}
	
	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap modelMap,@PathVariable("categoryId") Integer categoryId) {
		
		Optional<Category> opt=categoryService.findById(categoryId); 
		CategoryDto dto=new CategoryDto();
		if(opt.isPresent()) {
			Category entity=opt.get();
			
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			
			modelMap.addAttribute("category", dto);
			return new ModelAndView("adminCategoryAdd",modelMap) ;
		}
		
		modelMap.addAttribute("message", "Category is not exited!");
		return new ModelAndView("redirect:/admin/categories",modelMap);
	}
	
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
		@Valid @ModelAttribute("category")CategoryDto dto,BindingResult result) {
		if(result.hasErrors()) {
			
			return new ModelAndView("adminCategoryAdd");
		}
		Category entity=new Category();
		BeanUtils.copyProperties(dto, entity);
		
		categoryService.save(entity);
		session=request.getSession();
		session.setAttribute("message", "Category is saved!");
		return new ModelAndView("redirect:/admin/categories",model);
	}
	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model,@PathVariable("categoryId") Integer categoryId) {
		categoryService.deleteById(categoryId);
		session=request.getSession();
		session.setAttribute("message", "Category is deleted!");
		return new ModelAndView("redirect:/admin/categories",model);
	}
//
//	@GetMapping("search")
//	public String search(ModelMap model,@RequestParam(name="name",required = false)String name) {
//		List<Category> list=null;	
//		if(StringUtils.hasText(name)) {
//				list =categoryService.findByNameContaining(name);
//			}else {
//				list =categoryService.findAll();
//			}
//		model.addAttribute("categories", list);
//		return "admin/categories/search";
//	}
	
	@GetMapping(value = {"/category", "/", ""})
	public String search(ModelMap model,
			@RequestParam(name="name",required = false)String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size
			) {
		int currentPage=page.orElse(1);
		int pageSize=size.orElse(4);
		
		Pageable pageable=PageRequest.of(currentPage -1, pageSize,Sort.by("name"));
		Page<Category> resultPage=null;
		
		if(StringUtils.hasText(name)) {
			resultPage =categoryService.findByNameContaining(name,pageable);
			}else {
				resultPage =categoryService.findAll(pageable);
			}
		
		int totalPages=resultPage.getTotalPages();
		if(totalPages > 0) {
			int start=Math.max(1, currentPage-2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if(totalPages > 5 ) {
				if(end == totalPages) start=end-2;
				else if (start ==1) end=start+2;
			}
			List<Integer> pageNumber=IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			//chuyển danh sách các gtri sinh ra đc từ trong khoảng từ start 
			//-> end và chuyển thành danh sách
			
			model.addAttribute("pageNumber", pageNumber);
		}
		model.addAttribute("categoryPage", resultPage);
		return "adminCategoryList";
	}
}
