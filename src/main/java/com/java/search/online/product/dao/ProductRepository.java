package com.java.search.online.product.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.search.online.product.sql.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	}
