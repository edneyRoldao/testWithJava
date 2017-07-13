package com.edn.selenium.samples.mainTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContatoPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public ContatoPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public void visit() {
		driver.get("http://localhost:3000/#/novoContato");
	}

	public void createNewUser(String... fields) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nome")));
		
		driver.findElement(By.name("nome")).clear();
		driver.findElement(By.name("nome")).sendKeys(fields[0]);
		
		driver.findElement(By.name("telefone")).clear();
		driver.findElement(By.name("telefone")).sendKeys(fields[1]);
		
		driver.findElement(By.name("data")).clear();
		driver.findElement(By.name("data")).sendKeys(fields[2]);
		
	    new Select(driver.findElement(By.name("opera"))).selectByVisibleText(fields[3]);
	    
	    driver.findElement(By.name("addContato")).click();
	}
	

}
