package com.krishtech.orders.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishtech.orders.domain.Product;
import com.krishtech.orders.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String productId) {
        return productRepository.findOne(productId);
    }
    
    /**
     *  Retrieve/Fetch Products with price greater than the input
     *  @Param - Baseline price
     *  @return - List
     */
    public List<Product> listProductsByGreaterThanPrice(final BigDecimal price) {
    	return productRepository.findByPriceGreaterThan(price);
    }
}
