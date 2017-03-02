package com.api.utils;

import static io.restassured.RestAssured.given;

import java.net.URI;

import org.apache.http.HttpStatus;

import io.restassured.response.Response;

public class Utilites {

	public static Response getResponseUri(URI uri) {
		return given().when().get(uri);
	}

	//public static Response getStatusCode(Response response) {
		//return response.then().assertThat().statusCode(response);
		//return (Response) response.then().assertThat().statusCode(HttpStatus.SC_OK);
	//}
	
	
}
