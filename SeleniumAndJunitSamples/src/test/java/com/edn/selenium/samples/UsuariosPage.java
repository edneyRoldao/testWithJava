package com.edn.selenium.samples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuariosPage {

	private WebDriver driver;

	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://localhost:8080/usuarios");
	}

	public void visita() {
		driver.get("http://localhost:8080/usuarios");
	}

	public UsuarioPage novo() {
		driver.findElement(By.linkText("Novo Usuário")).click();
		return new UsuarioPage(driver);
	}

	public boolean existeNaLista(String nome, String email) {
		String content = driver.getPageSource();
		return content.contains(nome) && content.contains(email);
	}

}
