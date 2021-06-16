package com.vn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vn.entity.Account;

@Service
public interface AccountService {

	void delete(Account entity);

	void deleteById(Integer id);

	<S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Account> findById(Integer id);

	List<Account> findAll(Sort sort);

	List<Account> findAll();

	Page<Account> findAll(Pageable pageable);

	Account save(Account entity);

	Account findByEmail(String email);

	Account getById(Integer id);


}
