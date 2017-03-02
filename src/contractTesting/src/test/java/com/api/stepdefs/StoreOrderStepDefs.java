package com.api.stepdefs;

import com.api.config.Hooks;
import com.api.servicesTests.StoreOrderService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StoreOrderStepDefs extends Hooks {

	@Given("^To accept response in JSON format$")
	public void to_accept_response_in_JSON_format() throws Throwable {
		Hooks.Setup();
		StoreOrderService.contentType();
	}

	@When("^I perform GET request$")
	public void i_perform_GET_request() throws Throwable {

		StoreOrderService.getRequest();
	}

	@Then("^I validate service reponse$")
	public void i_validate_service_reponse() throws Throwable {

		StoreOrderService.serviceCode();
	}

	@Then("^I validate size of the reponse$")
	public void i_validate_size_of_the_reponse() throws Throwable {

		StoreOrderService.responseSize();
	}

	@Then("^I validate content of the reponse$")
	public void i_validate_content_of_the_reponse() throws Throwable {
		StoreOrderService.responseContent();

	}

}
