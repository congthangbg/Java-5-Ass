package com.vn.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entity.Order;
import com.vn.model.OrderDTO;




@Service
public class OrderMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public Order convertToEntity(OrderDTO OrderDTO) {
		Order entity = modelMapper.map(OrderDTO, Order.class);
		return entity;
	}
	
	public OrderDTO convertToDTO(Order Order) {
		OrderDTO dto = modelMapper.map(Order, OrderDTO.class);
		return dto;
	}
	
	public List<Order> convertToListEntity(List<OrderDTO> list){
		List<Order> listEntity = new ArrayList<Order>();
		for (int i = 0; i < list.size(); i++) {
			OrderDTO OrderDTO = list.get(i);
			Order entity = convertToEntity(OrderDTO);
			listEntity.add(entity);
		}
		return listEntity;
	}
	
	public List<OrderDTO> convertToListDTO(List<Order> list){
		List<OrderDTO> listDTO = new ArrayList<OrderDTO>();
		for (int i = 0; i < list.size(); i++) {
			Order entity = list.get(i);
			OrderDTO dto = convertToDTO(entity);
			listDTO.add(dto);
		}
		return listDTO;
	}
}