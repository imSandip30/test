package com.ist.testrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(

		features = "src/main/resources", 
		tags = "", 
		glue = { 
				  "com.ist.stepdef"
				}, 
		plugin = { 
				    "pretty",				    
		           "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
				 },
		monochrome = false
		)

public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
