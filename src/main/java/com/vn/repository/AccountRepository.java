package com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("SELECT ac FROM Account ac WHERE email=:email")
	public Account findByEmail(@Param("email") String email);
}
