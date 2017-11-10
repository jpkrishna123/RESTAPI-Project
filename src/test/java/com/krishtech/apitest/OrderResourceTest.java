package com.krishtech.apitest;

import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.krishtech.base.BaseIntegrationTest;

public class OrderResourceTest extends BaseIntegrationTest {

	
	
	/*
	 * Test All order retrieval
	 */
	@Test
	public void testGetOrders() {
		
	
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("orders"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"orderNumber\":\"RTL_1001\",\"taxPercent\":10.00,\"total\":59.98,\"totalTax\":5.99,\"grandTotal\":65.97,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99},{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99}]},{\"orderNumber\":\"RTL_1002\",\"discount\":15.55,\"taxPercent\":10.00,\"total\":19.99,\"totalTax\":1.99,\"grandTotal\":6.43,\"status\":\"FULFILLED\",\"products\":[{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99}]},{\"orderNumber\":\"RTL_1003\",\"discount\":19.99,\"taxPercent\":8.50,\"total\":139.97,\"totalTax\":11.89,\"grandTotal\":131.87,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99},{\"upc\":\"1258793283\",\"sku\":\"7394950140000\",\"description\":\"True Skinny Jeans\",\"price\":49.99}]},{\"orderNumber\":\"RTL_1004\",\"taxPercent\":10.00,\"total\":109.98,\"totalTax\":10.99,\"grandTotal\":120.97,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99}]},{\"orderNumber\":\"RTL_1005\",\"taxPercent\":9.50,\"total\":49.99,\"totalTax\":4.74,\"grandTotal\":54.73,\"status\":\"FULFILLED\",\"products\":[{\"upc\":\"1258793283\",\"sku\":\"7394950140000\",\"description\":\"True Skinny Jeans\",\"price\":49.99}]}]";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	/*
	 * Test Order retrieval for a given order id
	 */
	@Test
	public void testGetOrder() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("orders/1"),
				HttpMethod.GET, entity, String.class);
		String expected = "{\"orderNumber\":\"RTL_1001\",\"taxPercent\":10.00,\"total\":59.98,\"totalTax\":5.99,\"grandTotal\":65.97,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99},{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99}]}";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Test No order in DB for a given order Id
	 */
	@Test
	public void testOrderNotFound() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("orders/-1000"),
				HttpMethod.GET, entity, String.class);
		String expected = null;
		try {
			assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
