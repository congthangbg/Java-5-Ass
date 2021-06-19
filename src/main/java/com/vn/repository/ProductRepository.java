package com.vn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.entity.Account;
import com.vn.entity.Category;
import com.vn.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByNameContaining(String name);
	
	Page<Product> findByNameContaining(String name,Pageable pageable);
	
	Product findByIdContaining(Integer id);
	
	@Query(value =  "select * from products inner join categories on categories.category_id=products.category_id\r\n"
			+ "where categories.category_id = ?1",nativeQuery = true)
	public List<Product> filterProductByCateId(Integer cateId);
}
