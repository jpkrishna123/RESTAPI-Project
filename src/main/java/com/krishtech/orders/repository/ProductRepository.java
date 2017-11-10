package com.krishtech.orders.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishtech.orders.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {
	/**
	 * Retrieve/Fetch List of products with price greater than given price
	 * @param price -  price as BigDecimal
	 * @return - List of products 
	 */
	public List<Product> findByPriceGreaterThan(final BigDecimal price);
}
