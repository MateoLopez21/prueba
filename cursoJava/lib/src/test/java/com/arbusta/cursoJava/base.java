package com.arbusta.cursoJava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class base {
	
	public static WebDriver configuration() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions(); // Instanciamos las opciones
		
		options.addArguments("--incognito"); // Agregamos las opciones
		
		WebDriver driver = new ChromeDriver(options);
		
		driver = new ChromeDriver(options); // Le especificamos que navegador vamos a utilizar.
		
		driver.manage().window().maximize(); // va a maximizar la ventana.
		

		// Abrir la página de SWAG LABS
		driver.get("https://www.saucedemo.com/#");
		
		return driver;
	}
	
}
