package com.edn.selenium.samples.mainTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:\\workspace\\geckodriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("http://localhost:3000/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 1);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new")));
		driver.findElement(By.id("new")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nome")));
		driver.findElement(By.name("nome")).clear();
		driver.findElement(By.name("nome")).sendKeys("joao das couves");
		
		driver.findElement(By.name("telefone")).clear();
		driver.findElement(By.name("telefone")).sendKeys("5656-5454");
		
		driver.findElement(By.name("data")).clear();
		driver.findElement(By.name("data")).sendKeys("12/12/1992");
		
	    new Select(driver.findElement(By.name("opera"))).selectByVisibleText("Vivo");
	    driver.findElement(By.name("addContato")).click();
		
		driver.close();
		System.out.println("Process is completed !");
	}
	
}
