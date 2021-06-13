package com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.vn.entity.Account;
import com.vn.entity.OrderDetail;
import com.vn.entity.Product;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
