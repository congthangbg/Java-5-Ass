package com.vn.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entity.OrderDetail;
import com.vn.model.CartItemDto;
import com.vn.model.OrderDetailDTO;


@Service
public class OrderDetailMapper {
	@Autowired
	private  ModelMapper modelMapper;
	
	public  OrderDetail convertToEntity(CartItemDto cartItemDto) {
		OrderDetail entity = modelMapper.map(cartItemDto, OrderDetail.class);
		return entity;
	}
	
	public OrderDetailDTO convertToDTO(OrderDetail OrderDetail) {
		OrderDetailDTO dto = modelMapper.map(OrderDetail, OrderDetailDTO.class);
		return dto;
	}
	
	public List<OrderDetail> convertToListEntity(List<CartItemDto> list){
		List<OrderDetail> listEntity = new ArrayList<OrderDetail>();
		for (int i = 0; i < list.size(); i++) {
			CartItemDto cartItemDto = list.get(i);
			OrderDetail entity = convertToEntity(cartItemDto);
			listEntity.add(entity);
		}
		return listEntity;
	}
	
	public List<OrderDetailDTO> convertToListDTO(List<OrderDetail> list){
		List<OrderDetailDTO> listDTO = new ArrayList<OrderDetailDTO>();
		for (int i = 0; i < list.size(); i++) {
			OrderDetail entity = list.get(i);
			OrderDetailDTO dto = convertToDTO(entity);
			listDTO.add(dto);
		}
		return listDTO;
	}
}