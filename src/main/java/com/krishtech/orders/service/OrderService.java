package com.krishtech.orders.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishtech.orders.domain.Order;
import com.krishtech.orders.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(String orderId) {
        return orderRepository.findOne(orderId);
    }
    
    /**
     *  Retrieve/Fetch Orders for the given status
     *  @param - Shipping status as String
     *  @return - List
     */
    public List<Order> getOrdersByShipStatus(String status) {
    	if(status != null && !status.isEmpty()) {
    		status = status.toUpperCase();
    	}
    	return orderRepository.findByStatus(status);
    }
    
    /**
     * Retrieve/Fetch Orders with discount greater than given value. 
     * 
     * @param discount as BigDecimal
     * @return - List of orders
     */
    public List<Order> getOrdersByDiscount(final BigDecimal discount) {
    	return orderRepository.findByDiscountGreaterThan(discount);
    }
    
    /**
     * Retrieve/Fetch Orders with Product count greater than given value. 
     * @param count  as long
     * @return - List of orders
     */
    public List<Order> getOrdersByProductCount(final long count) {
    	return orderRepository.findByProductCountGreaterThan(count);
    }

}
