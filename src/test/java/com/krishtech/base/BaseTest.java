package com.krishtech.base;

import java.math.BigDecimal;
import java.util.List;

import com.krishtech.orders.domain.Order;
import com.krishtech.orders.domain.Product;

public class BaseTest {

	protected Order createOrder(String orderNumber, String status, 
			Double pct, List<Product> productList) {
		 Order order = new Order();
		 order.setProducts(productList);
		 order.setOrderNumber(orderNumber);
		 order.setStatus(status);
		 BigDecimal taxPct = new BigDecimal(pct);
		 order.setTaxPercent(taxPct);
		 return order;
	}
	 protected Product createProduct(String sku, String productId, String upc, String desc, BigDecimal price) {
		 Product p = new Product();
			p.setSku(sku);
			p.setUpc(upc);
			p.setProductId(productId);
			p.setDescription(desc);
			p.setPrice(price);
			
			return p;
	 }
}
