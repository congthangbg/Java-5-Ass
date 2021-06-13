package com.vn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.vn.entity.Category;

public interface CategoryService {

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	<S extends Category> List<S> findAll(Example<S> example);

	Category getById(Integer id);

	void deleteAll();

	Category getOne(Integer id);

	void delete(Category entity);

	void deleteById(Integer id);

	long count();

	<S extends Category> boolean exists(Example<S> example);

	<S extends Category> long count(Example<S> example);

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(Integer id);

	Optional<Category> findById(Integer id);

	List<Category> findAllById(Iterable<Integer> ids);

	List<Category> findAll(Sort sort);

	List<Category> findAll();

	Page<Category> findAll(Pageable pageable);

	Category save(Category entity);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	List<Category> findByNameContaining(String name);



}
