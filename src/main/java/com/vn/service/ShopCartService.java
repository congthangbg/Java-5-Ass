package com.vn.service;

import java.util.Collection;

import com.vn.model.CartItemDto;

public interface ShopCartService {

	int getCount();

	double getAmount();

	void update(int productId, int quantity);

	void clear();

	Collection<CartItemDto> getAllCartItems();

	void remove(int productId);

	void add(CartItemDto item);

	
}
