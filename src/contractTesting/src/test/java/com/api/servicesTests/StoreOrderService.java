package com.api.servicesTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;

import com.api.utils.Utilites;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreOrderService extends Utilites {

	static Response response;

	public static void contentType() {
		try {
			given().accept(ContentType.JSON);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void getRequest() throws URISyntaxException  {
			response = getResponseUri(new URI("/Hounslow"));
		
	}

	public static void serviceCode() throws IOException {
		try {
			response.then().assertThat().statusCode(HttpStatus.SC_OK);

			// getStatusCode(HttpStatus.SC_OK);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void responseSize() throws IOException {
		try {
			response.then().body("id", hasSize(3));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void responseContent() throws IOException {
		try {
			response.then().body("id", hasItems("2b79f449-31c9-4a81-8b1f-d4a0e646de4f"));
			response.then().body("store", hasItems("Hounslow"));
			response.then().body("product", hasItems("Burger"));
			response.then().body("qty", hasItems("1000"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
