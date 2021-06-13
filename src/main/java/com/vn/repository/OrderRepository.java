package com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.vn.entity.Account;
import com.vn.entity.Order;
import com.vn.entity.Product;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
