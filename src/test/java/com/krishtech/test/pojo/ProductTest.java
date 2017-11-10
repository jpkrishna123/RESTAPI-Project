package com.krishtech.test.pojo;

import static org.junit.Assert.*;
import java.math.BigDecimal;

import org.junit.Test;

import com.krishtech.base.BaseIntegrationTest;
import com.krishtech.orders.domain.Product;

/*
 * Unit test Product domain object
 */
public class ProductTest extends BaseIntegrationTest {

	@Test
	public void testProduct() {
		
		BigDecimal price = new BigDecimal("24.33");
		Product p = createProduct("SKU001", "PD001", "UPC001","Test Product", price);
		assertTrue("SKU001".equals(p.getSku()));
		assertTrue("UPC001".equals(p.getUpc()));
		assertTrue("PD001".equals(p.getProductId()));
		assertTrue("Test Product".equals(p.getDescription()));
		assertTrue(price.compareTo(p.getPrice())==0);
				
	}
	
}
