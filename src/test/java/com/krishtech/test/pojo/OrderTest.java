package com.krishtech.test.pojo;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import com.krishtech.base.BaseIntegrationTest;
import com.krishtech.orders.domain.Order;
import com.krishtech.orders.domain.Product;

/*
 * Unit test Order domain object
 */
public class OrderTest extends BaseIntegrationTest {
	
	 @Test
	 public void testOrder(){
		 
		 Product p1 = createProduct("SKU001","PDC001","UPC001","Test Prod 1",new BigDecimal("15"));
		 Product p2 = createProduct("SKU002","PDC002","UPC002","Test Prod 2",new BigDecimal("25"));
		 
		 Order order = createOrder("ON001", "NEW", 
					10.00d, Arrays.asList(p1,p2)); 
		 
		 BigDecimal taxPct = new BigDecimal(10.00d);
		 assertTrue("ON001".equals(order.getOrderNumber()));
		 assertTrue("NEW".equals(order.getStatus()));
		 assertTrue(taxPct.compareTo(order.getTaxPercent())==0);
		 assertTrue(order.getProducts() != null && 2==order.getProducts().size());
		 assertTrue(order.getTotal().compareTo(new BigDecimal(40.0))==0);
		 assertTrue(order.getTotalTax().compareTo(new BigDecimal(4.0))==0);
		 assertTrue(order.getGrandTotal().compareTo(new BigDecimal(44.0))==0);
		 
	 }

	 
	
}
