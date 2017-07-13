package com.edn.selenium.samples.mainTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContatosListPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public ContatosListPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void visit() {
		driver.get("http://localhost:3000/#/contatos");
	}

	public ContatoPage newContato() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new")));
		driver.findElement(By.id("new")).click();
		
		return new ContatoPage(driver, wait);
	}
	
	public boolean isThereOnList(String... fields) {
		String content = driver.getPageSource();
		boolean contains = true;
		
		for(String field : fields) {
			if(!content.contains(field)) return false;
		}
		
		return contains;
	}

}
