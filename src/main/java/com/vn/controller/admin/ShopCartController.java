package com.vn.controller.admin;

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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
import com.vn.service.impl.ExcelService;
import com.vn.utils.DateFormat;

@Controller
@RequestMapping("admin/shoppingCart")
public class ShopCartController {
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
	ExcelService fileService;
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

		model.addAttribute("total", shoppingCartSevice.getAmount());
		model.addAttribute("NoOfItems", shoppingCartSevice.getCount());
		return "adminShopCart";
	}

	@GetMapping("save")
	public String createDB(Model model, @RequestParam("address") String address,HttpServletResponse response,RedirectAttributes reModelMap) throws IOException {
		if (address.equals("")) {
			model.addAttribute("errors", "Vui lòng nhập địa chỉ");

			return "forward:/admin/shoppingCart/views";
		}
		
		// lấy ra tk đã đăng nhập
		Account account = (Account) session.getAttribute("user");

		Date date = new Date();
		// save Order vào DB
		order.setAccount(account);
		order.setAddress(address);
		order.setCreateDate(date);
		orderRepository.save(order);

		// set OrderDetail to DB

		Order orderNewSave = orderRepository.getOrderByFiled(date, account, address);

		// convert Collection<CartItemDto> ->>>> List
		Collection<CartItemDto> cartItems = shoppingCartSevice.getAllCartItems();
		List<CartItemDto> listEntity1 = cartItems.stream().collect(Collectors.toList());
		List<OrderDetail> listEntity = orderDetailMapper.convertToListEntity(listEntity1);

		// set lại khóa (Order,Product) cho orderDetail
		for (OrderDetail orderDetail : listEntity) {
			orderDetail.setOrder(orderNewSave);
			Optional<Product> product = productService.findById(orderDetail.getId());
			orderDetail.setProduct(product.get());
		}

		List<OrderDetail> listOrderDetails =  orderDetailService.saveAll(listEntity);
		
		model.addAttribute("cartItems", listEntity1);
		model.addAttribute("total", shoppingCartSevice.getAmount());
        HttpSession session = request.getSession();
        session.setAttribute("status", true);
        session.setAttribute("message1", "Bạn đã thanh toán thành công!");
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
		return "redirect:/admin";
	}

	@GetMapping("remove/{productId}")
	public String remove(@PathVariable("productId") Integer productId) {
		shoppingCartSevice.remove(productId);
		return "adminShopCart";
	}

	@PostMapping("update")
	public String update(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {

		shoppingCartSevice.update(id, quantity);
		return "redirect:/admin/shoppingCart/views";
	}

	@GetMapping("clear")
	public String clear() {
		shoppingCartSevice.clear();
		return "redirect:/admin/shoppingCart/views";
	}

	@GetMapping("/download")
	public ResponseEntity<InputStreamResource> getFile(Model model) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String filename = "users-" + currentDateTime + ".xlsx";
		InputStreamResource file = new InputStreamResource(fileService.load());
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
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
