package com.edn.selenium.samples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsuarioPage {
	
	private WebDriver driver;

	public UsuarioPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://localhost:8080/usuarios/new");
	}
	
	public void visita() {
		driver.get("http://localhost:8080/usuarios/new");
	}
	
	public void cadastra(String nome, String email) {
		WebElement Enome = driver.findElement(By.name("usuario.nome"));
		WebElement Eemail = driver.findElement(By.name("usuario.email"));

		Enome.sendKeys(nome);
		Eemail.sendKeys(email);
		
		Enome.submit();
	}

}
