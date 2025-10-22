package com.project.RunCucumber;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.project.StepDefn.Base_class;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty"},
		features = {"src//test//resources//features//" },
		glue = { "com.project.StepDefn" },
		dryRun = false, tags= "@all" ,monochrome = true)


public class Runner extends Base_class {

	@AfterClass
	public static void ClosetheBrowser() {
		driver.quit();

	}
	
	
	
}
