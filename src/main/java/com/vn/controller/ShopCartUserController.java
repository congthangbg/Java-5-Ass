package com.vn.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.Export.UserExcelExporter;
import com.vn.entity.Account;
import com.vn.entity.Order;
import com.vn.entity.OrderDetail;
import com.vn.entity.Product;
import com.vn.mapper.OrderDetailMapper;
import com.vn.model.CartItemDto;
import com.vn.repository.OrderRepository;
import com.vn.service.OrderDetailService;
import com.vn.service.ProductService;
import com.vn.service.ShopCartService;
import com.vn.utils.DateFormat;

@Controller
@RequestMapping("user/shoppingCart")
public class ShopCartUserController {
	@Autowired
	ProductService productService;

	@Autowired
	ShopCartService shoppingCartSevice;
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	OrderDetailMapper orderDetailMapper;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Order order;

	@GetMapping("views")
	public String listView(Model model) {
		Collection<CartItemDto> cartItems = shoppingCartSevice.getAllCartItems();

		model.addAttribute("cartItems", cartItems);
//		for (CartItemDto cartItemDto : cartItems) {
//			System.out.println(cartItemDto.getName() + "--" + cartItemDto.getProductId()+"--"+cartItemDto.getPrice()+"--"+cartItemDto.getQuantity());
//		}
	
		model.addAttribute("total", shoppingCartSevice.getAmount());
		model.addAttribute("NoOfItems", shoppingCartSevice.getCount());
		return "shopCart";
	}
	
	
	@GetMapping("save")
	public String createDB(Model model,@RequestParam("address") String address) {
		if(address.equals("")) {
			model.addAttribute("errors","Vui lòng nhập địa chỉ");
		
			return "forward:/user/shoppingCart/views";
		}
		//lấy ra tk đã đăng nhập
		Account account = (Account) session.getAttribute("user");
		
		Date date=new Date();
		//save Order vào DB
		order.setAccount(account);
		order.setAddress(address);
		order.setCreateDate(date);
		orderRepository.save(order);

		//set OrderDetail to DB
		
		Order orderNewSave = orderRepository.getOrderByFiled(date, account, address);

		//convert Collection<CartItemDto> ->>>> List
		Collection<CartItemDto> cartItems = shoppingCartSevice.getAllCartItems();
		List<CartItemDto> listEntity1 = cartItems.stream().collect(Collectors.toList());
		List<OrderDetail> listEntity = orderDetailMapper.convertToListEntity(listEntity1);
		
		//set lại khóa (Order,Product) cho orderDetail
		for (OrderDetail orderDetail : listEntity) {
			orderDetail.setOrder(orderNewSave);
			Optional<Product> product=productService.findById(orderDetail.getId());
			orderDetail.setProduct(product.get());
		}	
		
		orderDetailService.saveAll(listEntity);
		model.addAttribute("cartItems", listEntity1);
		model.addAttribute("total", shoppingCartSevice.getAmount());
        HttpSession session = request.getSession();
        session.setAttribute("status", true);
        session.setAttribute("message1", "Bạn đã thanh toán thành công!");
		model.addAttribute("message", "Bạn đã thanh toán thành công!");
		
		return "shopCart";
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
		return "redirect:/user/shoppingCart/views";
	}

	@GetMapping("remove/{productId}")
	public String remove(@PathVariable("productId") Integer productId) {
		shoppingCartSevice.remove(productId);
		return "redirect:/user/shoppingCart/views";
	}

	@PostMapping("update")
	public String update(@RequestParam("id") Integer id,
			@RequestParam("quantity") Integer quantity) {
	
		shoppingCartSevice.update(id, quantity);
		return "redirect:/user/shoppingCart/views";
	}

	@GetMapping("clear")
	public String clear() {
		shoppingCartSevice.clear();
		return "redirect:/user/shoppingCart/views";
	}
	
	@GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
    	Collection<CartItemDto> cartItems = shoppingCartSevice.getAllCartItems();
		List<CartItemDto> listEntity1 = cartItems.stream().collect(Collectors.toList());
		List<OrderDetail> listEntity = orderDetailMapper.convertToListEntity(listEntity1);
         
		String total=String.valueOf(shoppingCartSevice.getAmount());
		Account account = (Account) session.getAttribute("user");
        UserExcelExporter excelExporter = new UserExcelExporter(listEntity,total,account,order);
         
        excelExporter.export(response);  
        
    }  
}
