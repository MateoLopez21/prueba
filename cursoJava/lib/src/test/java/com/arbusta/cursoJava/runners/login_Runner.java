package com.arbusta.cursoJava.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/login.feature",
					glue = "com.arbusta.cursoJava.stepDefinition",
					tags = "")
public class login_Runner {
	
	

}
