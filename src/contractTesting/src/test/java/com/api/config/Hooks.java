package com.api.config;

import org.junit.BeforeClass;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

public class Hooks {

	@BeforeClass
	public static void Setup() {

		baseURI = "http://40.68.251.112";
		port = 8089;
		basePath = "/orders/getStoreOrder";

		//baseURI = "http://104.40.220.4";
			//	port = 8089;
			//	basePath = "/orders/getStoreOrder";
		
		//http://104.40.220.4:8089/orders/getStoreOrder/Hounslow	
			
	}



}
