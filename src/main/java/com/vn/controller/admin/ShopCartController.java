package com.vn.controller.admin;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entity.Product;
import com.vn.model.CartItemDto;
import com.vn.service.ProductService;
import com.vn.service.ShopCartService;

@Controller
@RequestMapping("admin/shoppingCart")
public class ShopCartController {
	@Autowired
	ProductService productService;

	@Autowired
	ShopCartService shoppingCartSevice;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	@GetMapping("views")
	public String listView(Model model) {
		Collection<CartItemDto> cartItems = shoppingCartSevice.getAllCartItems();

		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", shoppingCartSevice.getAmount());
		model.addAttribute("NoOfItems", shoppingCartSevice.getCount());
		return "adminShopCart";
	}

	@GetMapping("add/{productId}")
	public String add(@PathVariable("productId") Integer productId) {
		Optional<Product> product = productService.findById(productId);
		if (product != null) {
			CartItemDto item = new CartItemDto();
			item.setName(product.get().getName());
			item.setPrice(product.get().getPrice());
			item.setProductId(product.get().getId());
			item.setQuantity(1);
			shoppingCartSevice.add(item);
		}
		return "redirect:/admin/shoppingCart/views";
	}

	@GetMapping("remove/{productId}")
	public String remove(@PathVariable("productId") Integer productId) {
		shoppingCartSevice.remove(productId);
		return "redirect:/admin/shoppingCart/views";
	}

	@PostMapping("update")
	public String update(@RequestParam("id") Integer id,
			@RequestParam("quantity") Integer quantity) {
	
		shoppingCartSevice.update(id, quantity);
		return "redirect:/admin/shoppingCart/views";
	}

	@GetMapping("clear")
	public String clear() {
		shoppingCartSevice.clear();
		return "redirect:/admin/shoppingCart/views";
	}
}
