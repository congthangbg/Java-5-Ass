package com.vn.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vn.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	
	@NotEmpty(message = "Username not empty!")
	private String name;
	@NotNull(message = "Price not empty !")
	private Double price;
	
	private String image;
	
	private Category category;
	
	private Boolean available=true;
	
	private Boolean isEdit=false;
}
