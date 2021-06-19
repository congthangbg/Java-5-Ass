package com.vn.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.vn.entity.Account;
import com.vn.entity.Order;
import com.vn.model.CartItemDto;
import com.vn.repository.OrderRepository;
import com.vn.service.ShopCartService;

@Service
@SessionScope
public class ShopCartServiceImpl implements ShopCartService{
	private Map<Integer, CartItemDto> map = new HashMap<Integer, CartItemDto>();//gio hang
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	
	@Override
	public void add(CartItemDto item) {
		CartItemDto existedItem = map.get(item.getProductId());
		
		if (existedItem != null) {
			existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
			
		} else {
			map.put(item.getProductId(), item);
		}
	}
	
	@Override
	public Order getOrderByFiled(Date createDate, Account account, String address) {
		return orderRepository.getOrderByFiled(createDate, account, address);
	}

	@Override
	public void remove(int productId) {
		map.remove(productId);
	}

	@Override
	public Collection<CartItemDto> getAllCartItems() {
		return map.values();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public void update(int productId, int quantity) {
		CartItemDto item = map.get(productId);

		item.setQuantity(quantity);

		if (item.getQuantity() <= 0) {
			map.remove(productId);
		}
	}

	@Override
	public double getAmount() {
		return map.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
	}
	@Override
	public int getCount() {
		if(map.isEmpty()) {
			return 0;
		}
		return map.values().size();
	}
	
	
}
