package com.krishtech.apitest;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.krishtech.base.BaseIntegrationTest;

public class SearchResourceTest extends BaseIntegrationTest {

	
	@Test
	public void testSearchByOrderStatus() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/orders?fieldName=status&value=shipped"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"orderNumber\":\"RTL_1001\",\"taxPercent\":10.00,\"total\":59.98,\"totalTax\":5.99,\"grandTotal\":65.97,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99},{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99}]},{\"orderNumber\":\"RTL_1003\",\"discount\":19.99,\"taxPercent\":8.50,\"total\":139.97,\"totalTax\":11.89,\"grandTotal\":131.87,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99},{\"upc\":\"1258793283\",\"sku\":\"7394950140000\",\"description\":\"True Skinny Jeans\",\"price\":49.99}]},{\"orderNumber\":\"RTL_1004\",\"taxPercent\":10.00,\"total\":109.98,\"totalTax\":10.99,\"grandTotal\":120.97,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99}]}]";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegSearchByOrderStatus() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/orders?fieldName=status&value=notExist"),
				HttpMethod.GET, entity, String.class);
		String expected = "No results found for the given search criteria.";
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(expected.equals(response.getBody()));
	}
	
	@Test
	public void testSearchByOrderDiscountApplied() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/orders?fieldName=discount&value=0.0"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"orderNumber\":\"RTL_1002\",\"discount\":15.55,\"taxPercent\":10.00,\"total\":19.99,\"totalTax\":1.99,\"grandTotal\":6.43,\"status\":\"FULFILLED\",\"products\":[{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99}]},{\"orderNumber\":\"RTL_1003\",\"discount\":19.99,\"taxPercent\":8.50,\"total\":139.97,\"totalTax\":11.89,\"grandTotal\":131.87,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99},{\"upc\":\"1258793283\",\"sku\":\"7394950140000\",\"description\":\"True Skinny Jeans\",\"price\":49.99}]}]";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegSearchByOrderDiscountApplied() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/orders?fieldName=discount&value=100000"),
				HttpMethod.GET, entity, String.class);
		String expected = "No results found for the given search criteria.";
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(expected.equals(response.getBody()));
	}
	
	@Test
	public void testSearchByOrderWithMultipleProducts() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/orders?fieldName=productcounts&value=2"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"orderNumber\":\"RTL_1003\",\"discount\":19.99,\"taxPercent\":8.50,\"total\":139.97,\"totalTax\":11.89,\"grandTotal\":131.87,\"status\":\"SHIPPED\",\"products\":[{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99},{\"upc\":\"1258793283\",\"sku\":\"7394950140000\",\"description\":\"True Skinny Jeans\",\"price\":49.99}]}]";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegSearchByOrderWithMultipleProducts() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/orders?fieldName=productcounts&value=100000"),
				HttpMethod.GET, entity, String.class);
		String expected = "No results found for the given search criteria.";
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(expected.equals(response.getBody()));
	}
	
	@Test
	public void testSearchByProductWithGreaterPrice() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/products?fieldName=price&value=30"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99},{\"upc\":\"1258793283\",\"sku\":\"7394950140000\",\"description\":\"True Skinny Jeans\",\"price\":49.99}]";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegSearchByProductWithGreaterPrice() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/products?fieldName=price&value=100000"),
				HttpMethod.GET, entity, String.class);
		String expected = "No results found for the given search criteria.";
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(expected.equals(response.getBody()));
	}
	
	
	
	
	@Test
	public void testOrderInvalidFilter() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/orders?fieldName=test&value1=20"),
				HttpMethod.GET, entity, String.class);
		String expected = "Invalid search criteria name";
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(expected.equals(response.getBody()));
	}
	
	@Test
	public void testInvalidProductFilter() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/search/products?fieldName=test&value=30"),
				HttpMethod.GET, entity, String.class);
		String expected = "Invalid search criteria name";
		assertTrue(expected.equals(response.getBody()));
	}

	
}
