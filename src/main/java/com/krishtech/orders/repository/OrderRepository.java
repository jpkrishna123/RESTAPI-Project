package com.krishtech.orders.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.krishtech.orders.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {
	/**
	 * get orders by status
	 * @param status - Order status as String
	 * @return - List of orders 
	 */
	public List<Order> findByStatus(final String status);
	
	/**
	 * Retrieve/fetch orders that have discount greater than the given discount parameter 
	 * @param discount -  discount as BigDecimal
	 * @return - List of orders
	 */
	public List<Order> findByDiscountGreaterThan(final BigDecimal discount);
	
	/**
	 * Retrieve/fetch orders with number of product count greater than given count
	 * @param count -  product count as long
	 * @return - List of orders 
	 */
	@Query("select o from Order o where (select count(product) from o.products product) > :count")
	public List<Order> findByProductCountGreaterThan(final @Param("count") long count);
	
}
