package com.krishtech.orders.resource;

import static org.springframework.http.ResponseEntity.ok;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krishtech.orders.constants.SearchConstant;
import com.krishtech.orders.constants.SearchField;
import com.krishtech.orders.domain.Order;
import com.krishtech.orders.domain.Product;
import com.krishtech.orders.service.OrderService;
import com.krishtech.orders.service.ProductService;

@RestController
@RequestMapping("/search")
public class SearchResource {

	@Autowired
    private OrderService orderService;
	
	@Autowired
    private ProductService productService;
	
	/**
	 * Retrieve/Fetch List of orders matching specified search condition
	 * @param filed Name - attribute name used for search condition. status,discount, product counts and price
	 * @param value -  value, used to filter Orders or it will use default value.
	 * @return - List of orders for the given filter criteria. Returned to client as a JSON string
	 */
	@GetMapping("/orders")
	public ResponseEntity<?> searchOrders(@RequestParam(value="fieldName", required=true) String fieldName,
			@RequestParam(value="value", required=false) String value) {
		
		List<Order> orderList = null;
		
		try {
		if (fieldName != null && !fieldName.isEmpty()) {
			if(fieldName.trim().equalsIgnoreCase(SearchField.STATUS.toString())) {
				value = value != null && !value.isEmpty() ? value.trim() : SearchConstant.SHIPPED_STATUS;
				orderList = orderService.getOrdersByShipStatus(value);
			}else if(fieldName.trim().equalsIgnoreCase(SearchField.DISCOUNT.toString())) {
				BigDecimal discount = value != null && !value.isEmpty() ? new BigDecimal(value) : new BigDecimal(0.0);
				orderList = orderService.getOrdersByDiscount(discount);
			}else if(fieldName.trim().equalsIgnoreCase(SearchField.PRODUCT_COUNT.toString())) {
				long count = value != null && !value.isEmpty() ? new Long(value) : SearchConstant.DEFAULT_PRODUCT_COUNT;
				orderList = orderService.getOrdersByProductCount(count);
			}else {
				return ok(new String("Invalid search criteria name"));
			}
			
		}} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (orderList != null && !orderList.isEmpty()) {
			return ok(orderList);
		} else {
			return ok(new String("No results found for the given search criteria."));
		}
		
	}
	
	/**
	 * Retrieve/Fetch List of orders matching specified search condition
	 * @param filed Name - attribute name used for search condition. status,discount, product counts and price
	 * @param value -  value, used to filter Orders or it will use default value.
	 * @return - List of orders for the given filter criteria. Returned to client as a JSON string
	 */
	@GetMapping("/products")
	public ResponseEntity<?> searchProducts(@RequestParam(value="fieldName", required=true) String fieldName,
			@RequestParam(value="value", required=false) String value) {
		
		List<Product> productList = null;
		
		try {
		if (fieldName != null && !fieldName.isEmpty()) {
			if(fieldName.trim().equalsIgnoreCase(SearchField.PRICE.toString())) {
				BigDecimal price = value != null && !value.isEmpty() ? new BigDecimal(value) : new BigDecimal(SearchConstant.DEFAULT_PRICE);
				productList = productService.listProductsByGreaterThanPrice(price);
			}else {
				return ok(new String("Invalid search criteria name"));
			}
			
		}} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (productList != null && !productList.isEmpty()) {
			return ok(productList);
		} else {
			return ok(new String("No results found for the given search criteria."));
		}
		
	}
	
}
