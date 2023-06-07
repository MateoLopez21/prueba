package com.arbusta.cursoJava.stepDefinition;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.arbusta.cursoJava.base;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class login_Step extends base {
	
	private WebDriver driver;
	
	// Almacenamiento de localizadores 
	
	public By BTN_Login = By.id("login-button");
	public By LBL_EmptyUser = By.xpath("//h3[contains(text(), 'Epic sadface: Username is required')]");
	public By LBL_WrongCredentials = By.xpath("//h3[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]");
	public By LBL_TextHome = By.xpath("//div[contains(text(), 'Swag Labs')]");
	
	public By TXT_Username = By.id("user-name");
	public By TXT_Password = By.id("password");
	
	 @Given("^Un usuario que ingresa al login$")
	    public void un_usuario_que_ingresa_al_login() throws Throwable {
	        driver = configuration();
	    }

	    @When("^El usuario no ingresa ninguna credencial$")
	    public void el_usuario_no_ingresa_ninguna_credencial() throws Throwable {
	    	
	        driver.findElement(BTN_Login).click();
	    }

	    @Then("^La pagina muestra un mensaje de error indicando que el usuario esta vacio$")
	    public void la_pagina_muestra_un_mensaje_de_error_indicando_que_el_usuario_esta_vacio() throws Throwable {
	        
	    	Thread.sleep(5000);
	    	
	    	assertTrue(driver.findElement(LBL_EmptyUser).isDisplayed());
	    }
	    
	    
	    

	    @When("^El usuario ingresa credenciales invalidas$")
	    public void el_usuario_ingresa_credenciales_invalidas() throws Throwable {
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(TXT_Username));
	    	
	        driver.findElement(TXT_Username).sendKeys("asdasd");
	        driver.findElement(TXT_Password).sendKeys("asdasdas");
	        driver.findElement(BTN_Login).click();
	    }

	    @Then("^La pagina le muestra un mensaje de error indicando que las credenciales son invalidas$")
	    public void la_pagina_le_muestra_un_mensaje_de_error_indicando_que_las_credenciales_son_invalidas() throws Throwable {
	        
	    	Thread.sleep(5000);
	    	assertTrue(driver.findElement(LBL_WrongCredentials).isDisplayed());
	    	
	    }
	    
	    
	    
	    
	    @When("^El usuario ingresa credenciales validas$")
	    public void el_usuario_ingresa_credenciales_validas() throws Throwable {

	        driver.findElement(TXT_Username).sendKeys("standard_user");
	        driver.findElement(TXT_Password).sendKeys("secret_sauce");
	        driver.findElement(BTN_Login).click();
	    }

	    @Then("^La pagina valida credenciales e ingresa a la pagina principal$")
	    public void la_pagina_valida_credenciales_e_ingresa_a_la_pagina_principal() throws Throwable {
	        

	    	Thread.sleep(3000);
	    	assertTrue(driver.findElement(LBL_TextHome).isDisplayed());
	    	
	    }
	    
	    @After
	    public void TearDown() throws InterruptedException {
	    	Thread.sleep(2000);
	    	driver.quit();
	    }
	
}
