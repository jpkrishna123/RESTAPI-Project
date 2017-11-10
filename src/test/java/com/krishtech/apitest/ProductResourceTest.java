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

public class ProductResourceTest extends BaseIntegrationTest {
	
	
	/*
	 * Test All product retrieval
	 */
	@Test
	public void testGetProducts() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("products"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99},{\"upc\":\"1358743283\",\"sku\":\"7394650110003\",\"description\":\"Polo Shirt\",\"price\":19.99},{\"upc\":\"1458843283\",\"sku\":\"7394750120000\",\"description\":\"Floral Swing Skirt\",\"price\":69.99},{\"upc\":\"1358753283\",\"sku\":\"7394850130001\",\"description\":\"Denim Short\",\"price\":29.99},{\"upc\":\"1258793283\",\"sku\":\"7394950140000\",\"description\":\"True Skinny Jeans\",\"price\":49.99}]";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Order retrieval for a given product id
	 */
	@Test
	public void testGetProduct() {
	
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("products/1"),
				HttpMethod.GET, entity, String.class);
		String expected = "{\"upc\":\"1257833283\",\"sku\":\"9394550220002\",\"description\":\"Diva Jeans\",\"price\":39.99}";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Test No product in DB for a given product Id
	 */
	@Test
	public void testProductNotFound() {
	
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("products/-1000"),
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
