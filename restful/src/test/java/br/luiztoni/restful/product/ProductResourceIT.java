package br.luiztoni.restful.product;

import br.luiztoni.restful.category.Category;
import br.luiztoni.restful.config.ApiClient;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResourceIT {

	@Test
	public void integrationTest() throws IOException, InterruptedException {
		testBobPostCategory();
		testAlicePostProducts();
		testGuestGetProducts();
	}

	private void testBobPostCategory() throws IOException, InterruptedException {
		String port = System.getProperty("http.port");
		String url = "http://localhost:" + port + "/categories";
		ApiClient apiClient = new ApiClient();
		apiClient.setAuthorization("bob", "bobpwd");
		Category category = new Category("Energy Drinks", "Non alcoholics drinks");
		var response = apiClient.postRequest(url, category);
		assertEquals(Response.Status.CREATED.getStatusCode(), response.statusCode(), "Incorrect response code from " + url);
	}

	private void testAlicePostProducts() throws IOException, InterruptedException {
		String port = System.getProperty("http.port");
		String url = "http://localhost:" + port + "/products";
		ApiClient apiClient = new ApiClient();
		apiClient.setAuthorization("alice", "alicepwd");
		ProductRequest product1 = new ProductRequest("Red Bull","Energy Drink", 1L);
		var response1 = apiClient.postRequest(url, product1);
		assertEquals(Response.Status.CREATED.getStatusCode(), response1.statusCode(), "Incorrect response code from " + url);
		ProductRequest product2 = new ProductRequest("Monster","Energy Drink", 1L);
		var response2 = apiClient.postRequest(url, product2);
		assertEquals(Response.Status.CREATED.getStatusCode(), response2.statusCode(), "Incorrect response code from " + url);

	}

	private void testGuestGetProducts() throws IOException, InterruptedException {
		String port = System.getProperty("http.port");
		String url = "http://localhost:" + port + "/products";
		ApiClient apiClient = new ApiClient();
		var response = apiClient.getRequest(url);
		assertEquals(Response.Status.OK.getStatusCode(), response.statusCode(), "Incorrect response code from " + url);
		String json = response.body();
		Jsonb jsonb = JsonbBuilder.create();
		Product[] products = jsonb.fromJson(json, Product[].class);
		assertEquals(2, products.length, "response size can not be different of 0");
	}
}
