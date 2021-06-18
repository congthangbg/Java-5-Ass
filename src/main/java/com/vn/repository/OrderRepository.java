package com.vn.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.entity.Account;
import com.vn.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Query("SELECT entity FROM Order entity WHERE entity.account.id=:id")
	public List<Order> searchByIDUser(@Param("id") Integer idUser);

	@Query("SELECT e FROM Order e WHERE e.createDate = :createDate AND e.account = :account AND e.address = :address")
	public Order getOrderByFiled(@Param("createDate") Date createDate, @Param("account") Account account, @Param("address") String address);
	

}
