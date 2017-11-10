package com.krishtech.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.krishtech.base.BaseTest;
import com.krishtech.orders.domain.Product;
import com.krishtech.orders.repository.ProductRepository;
import com.krishtech.orders.service.ProductService;

public class ProductServiceTest extends BaseTest {
	
	@InjectMocks
	ProductService productService;
	
	@Mock
	ProductRepository productRepo;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetProduct() {
		
		 Product prod = createProduct("SKU001","PDC001","UPC001","Test Product 1",new BigDecimal("15"));
		 
		 when(productService.getProduct(anyString())).thenReturn(prod);
		 Product result = productService.getProduct("1");
		 assertThat("result", result, is(sameInstance(prod)));
		 verify(productRepo).findOne("1");
	}
	
	@Test
	public void testListProducts() {
		
		 Product p1 = createProduct("SKU001","PDC001","UPC001","Test Product 1",new BigDecimal("15"));
		 Product p2 = createProduct("SKU002","PDC002","UPC002","Test Product 2",new BigDecimal("25"));
	
		 List<Product> productList = Arrays.asList(p1,p2);
		 
		 when(productService.listProducts()).thenReturn(productList);
		 List<Product> results = productService.listProducts();
		 assertThat("result", results, is(sameInstance(productList)));
		 verify(productRepo).findAll();
	}
	
	@Test
	public void testListProductsByGreaterThanPrice() {
		 Product p1 = createProduct("SKU002","PDC002","UPC002","Test Product 2",new BigDecimal("15"));
		 List<Product> productList = Arrays.asList(p1);
		 BigDecimal baseLine = new BigDecimal("40");
		 when(productService.listProductsByGreaterThanPrice(baseLine)).thenReturn(productList);
		 List<Product> results = productService.listProductsByGreaterThanPrice(baseLine);
		 assertThat("result", results, is(sameInstance(productList)));
		 verify(productRepo).findByPriceGreaterThan(baseLine);
	}

}
