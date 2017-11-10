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
import com.krishtech.orders.domain.Order;
import com.krishtech.orders.domain.Product;
import com.krishtech.orders.repository.OrderRepository;
import com.krishtech.orders.service.OrderService;

/*
 * Unit test Order Service
 */
public class OrderServiceTest extends BaseTest {
	
	@InjectMocks
	OrderService orderService;

	@Mock
	OrderRepository orderRepo;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testFindOrder() {
		
		 Product p1 = createProduct("SKU001","PDC001","UPC001","Test Prod 1",new BigDecimal("35"));
		 Product p2 = createProduct("SKU002","PDC002","UPC002","Test Prod 2",new BigDecimal("45"));
		 
		 Order order = createOrder("ON001", "NEW", 
					5.00d, Arrays.asList(p1,p2)); 
		 
        when(orderService.getOrder(anyString())).thenReturn(order);
        
        Order result = orderService.getOrder("1");

        assertThat("result", result, is(sameInstance(order)));

        verify(orderRepo).findOne("1");
	}
	
	@Test
	public void testListOrders() {
		
		Product p1 = createProduct("SKU001","PDC001","UPC001","Test Prod 1",new BigDecimal("15"));
		Product p2 = createProduct("SKU002","PDC002","UPC002","Test Prod 2",new BigDecimal("25"));
		
		Order order1 = createOrder("ON001", "NEW", 
					5.00d, Arrays.asList(p1)); 
		Order order2 = createOrder("ON002", "NEW", 
				10.00d, Arrays.asList(p2)); 
		List<Order> orderList = Arrays.asList(order1,order2);
		when(orderService.listOrders()).thenReturn(orderList);
     
		List<Order> results = orderService.listOrders();

		assertThat("result", results, is(sameInstance(orderList)));

		verify(orderRepo).findAll();
	}
	
	@Test
	public void testListOrdersByDiscount() {
		
		Product p1 = createProduct("SKU001","PDC001","UPC001","Test Prod 1",new BigDecimal("15"));
		
		Order orders = createOrder("ON001", "NEW", 
					5.00d, Arrays.asList(p1)); 
		List<Order> orderList = Arrays.asList(orders);
		BigDecimal discount = new BigDecimal("10");
		when(orderService.getOrdersByDiscount(discount)).thenReturn(orderList);
     
		List<Order> results = orderService.getOrdersByDiscount(discount);

		assertThat("result", results, is(sameInstance(orderList)));

		verify(orderRepo).findByDiscountGreaterThan(discount);
	}
	
	@Test
	public void testListOrdersByMinProduct() {
		Product p1 = createProduct("SKU001","PDC001","UPC001","Test Prod 1",new BigDecimal("15"));
		Product p2 = createProduct("SKU002","PDC002","UPC002","Test Prod 2",new BigDecimal("25"));
		
	
		Order orders = createOrder("ON001", "NEW", 
				15.00d, Arrays.asList(p1,p2)); 
		List<Order> orderList = Arrays.asList(orders);
		when(orderService.getOrdersByProductCount(2)).thenReturn(orderList);
     
		List<Order> results = orderService.getOrdersByProductCount(2);

		assertThat("result", results, is(sameInstance(orderList)));

		verify(orderRepo).findByProductCountGreaterThan(2);
	}
	
	@Test
	public void testListOrdersByShipStatus() {
		Product p1 = createProduct("SKU001","PDC001","UPC001","Test Product 1",new BigDecimal("15"));
		Product p2 = createProduct("SKU002","PDC002","UPC002","Test Product 2",new BigDecimal("25"));
		
		Order orders = createOrder("ON002", "SHIPPED", 
				15.00d, Arrays.asList(p1,p2)); 
		List<Order> orderList = Arrays.asList(orders);
		when(orderService.getOrdersByShipStatus("SHIPPED")).thenReturn(orderList);
     
		List<Order> results = orderService.getOrdersByShipStatus("SHIPPED");

		assertThat("result", results, is(sameInstance(orderList)));

		verify(orderRepo).findByStatus("SHIPPED");
	}
}
