package com.api.apiDemo;

import java.net.URISyntaxException;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)


@CucumberOptions(tags = "@api" , glue = { "com.api" }, features = "src/test/resources/features", format = { "pretty",
		"json:target/cucumberreports/cucumber.json",
		"html:target/cucumberreports/cucumberhtml.html" }, monochrome = true)

/**
 * Unit test for simple App.
 */
public class AppTest {

	

}
