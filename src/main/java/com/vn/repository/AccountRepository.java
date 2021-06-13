package com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	
}
