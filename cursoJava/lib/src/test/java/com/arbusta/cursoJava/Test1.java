package com.arbusta.cursoJava;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Test1 {
	
	private WebDriver driver; // Instancia del WebDriver para poder interactuar con el navegador.
	
	// WebElements de la página ORANGE
	By TXT_orangeUsername = By.name("username");
	
	By TXT_orangePassword = By.name("password");
	
	By BTN_orangeLogin = By.className("oxd-button");
	
	// -------------------------
	
	// WebElements de la página SWAG LABS
	By TXT_swagUser = By.xpath("//input[@id='user-name']");
	By TXT_swagPassword = By.xpath("//input[@id='password']");
	By BTN_swagLogin = By.xpath("//input[@id='login-button']");
	By BTN_addItem1 = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
	By BTN_addItem2 = By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']");
	By BTN_cart = By.xpath("//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='header_container']/div[1]/div[3]/a[1]");
	By BTN_removeItem = By.xpath("//button[@id='remove-sauce-labs-backpack']");
	
	
	@Before
	public void configuration() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions(); // Instanciamos las opciones
		
		options.addArguments("--incognito"); // Agregamos las opciones
		
		driver = new ChromeDriver(options); // Le especificamos que navegador vamos a utilizar.
		
		driver.manage().window().maximize(); // va a maximizar la ventana.

		// Abrir la página de ORANGE
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		// Abrir la página de SWAG LABS
		driver.get("https://www.saucedemo.com/#");
		
		
	}
	
	
	// Test SWAG LABS
	@Test
	public void loginValid() throws InterruptedException {
		Thread.sleep(2000);

		driver.findElement(TXT_swagUser).sendKeys("standard_user");
		
		driver.findElement(TXT_swagPassword).sendKeys("secret_sauce");
		
		driver.findElement(BTN_swagLogin).click();
		
		Thread.sleep(2000);
		
		assertTrue(driver.findElement(BTN_cart).isDisplayed());
		
		System.out.println("Se inició sesión correctamente.");
			
	}
	
	@Test
	public void loginInvalid() throws InterruptedException {
		Thread.sleep(2000);

		driver.findElement(TXT_swagUser).sendKeys("standard_user");
		
		driver.findElement(TXT_swagPassword).sendKeys("secret_sauce1");
		
		driver.findElement(BTN_swagLogin).click();
		
		Thread.sleep(2000);
		
		assertTrue(driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]")).isDisplayed());
		
		System.out.println("Las credenciales no son válidas.");
	}
	
	@Test
	public void emptyLogin() throws InterruptedException {
		Thread.sleep(2000);
		
		driver.findElement(BTN_swagLogin).click();
		
		Thread.sleep(2000);
		
		assertTrue(driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]")).isDisplayed());
		
	}
	
	@Test
	public void addToCart() throws InterruptedException {
		Thread.sleep(2000);
		
		driver.findElement(TXT_swagUser).sendKeys("standard_user");
		
		driver.findElement(TXT_swagPassword).sendKeys("secret_sauce");
		
		driver.findElement(BTN_swagLogin).click();
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		
		assertEquals(expectedUrl, actualUrl);
		Thread.sleep(2000);
		
		driver.findElement(BTN_addItem1).click();
		driver.findElement(BTN_addItem2).click();
		
		Thread.sleep(2000);
		
		driver.findElement(BTN_cart).click();
		
		assertTrue(driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]")).isDisplayed());
		
		System.out.println("Se agregó correctamente al carrito");
		
		
	}
	
	@Test
	public void removeFromCart() throws InterruptedException {
		Thread.sleep(2000);
		


		driver.findElement(TXT_swagUser).sendKeys("standard_user");
		driver.findElement(TXT_swagPassword).sendKeys("secret_sauce");
		driver.findElement(BTN_swagLogin).click();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		
		assertEquals(expectedUrl, actualUrl);
		Thread.sleep(2000);
		
		driver.findElement(BTN_addItem1).click();
		driver.findElement(BTN_addItem2).click();
		
		Thread.sleep(2000);
		
		driver.findElement(BTN_cart).click();
		
		assertTrue(driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]")).isDisplayed());
		
		driver.findElement(BTN_removeItem).click();
		
		assertFalse(driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]")).isDisplayed());
		System.out.println("El elemento se removió del carrito.");
		
	}
	
	
	
	//Test ORANGE
	
	@Test
	public void orangeLoginValid() throws InterruptedException {
		Thread.sleep(2000);

		driver.findElement(TXT_orangeUsername).sendKeys("Admin");
		
		driver.findElement(TXT_orangePassword).sendKeys("admin123");
		
		driver.findElement(BTN_orangeLogin).click();
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		
		assertEquals(expectedUrl, actualUrl);
		
		System.out.println("Se inició sesión correctamente.");
			
	}
	
	@Test
	public void orangeLoginInvalid() throws InterruptedException {
		Thread.sleep(2000);

		driver.findElement(TXT_orangeUsername).sendKeys("Admin");
		
		driver.findElement(TXT_orangePassword).sendKeys("123admin");
		
		driver.findElement(BTN_orangeLogin).click();
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		
		assertEquals(expectedUrl, actualUrl);
		
		System.out.println("Las credenciale no son válidas.");
	}
	
	@Test
	public void orangeEmptyLogin() throws InterruptedException {
		Thread.sleep(2000);
		
		driver.findElement(BTN_orangeLogin).click();
		
		assertTrue(driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).isDisplayed());
			
		System.out.println("Los campos son obligatorios");
		
	}
	
	@Test
	public void forgotPassword() throws InterruptedException {
		Thread.sleep(2000);
		
		// Clic en Forgot your password?
		driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")).click();
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
		
		assertEquals(expectedUrl, actualUrl);
		Thread.sleep(2000);
		
		driver.findElement(TXT_orangeUsername).sendKeys("Admin");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String passwordRequest = driver.getCurrentUrl();
		String sendPassword = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset";
		
		assertEquals(sendPassword, passwordRequest);
		
		System.out.println("Contraseña reestablecida.");
	}
	
	@Test
	public void forgotPasswordEmpty() throws InterruptedException{
		Thread.sleep(2000);
		// Clic en Forgot your password?
		driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")).click();
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
		
		assertEquals(expectedUrl, actualUrl);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		assertTrue(driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).isDisplayed());
		System.out.println("Los campos son obligatorios");
		
		
	}
	
	
	
	
	@After
	public void shutDown() throws InterruptedException {
		
		Thread.sleep(5000); // Espera 5 segundos
		driver.quit(); // Cerrar el navegador y el proceso en segundo plano
	}
	

}
