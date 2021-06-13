package com.vn.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.vn.model.CartItemDto;
import com.vn.service.ShopCartService;

@Service
@SessionScope
public class ShopCartServiceImpl implements ShopCartService{
	private Map<Integer, CartItemDto> map = new HashMap<Integer, CartItemDto>();//gio hang

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
