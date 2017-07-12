package com.edn.selenium.samples;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TelaUsuarioTest {

	private WebDriver driver;
	UsuariosPage usuarios;
	
	@BeforeClass
	public static void loadDriver() {
		System.setProperty("webdriver.gecko.driver", "C:\\workspace\\geckodriver\\geckodriver.exe");
	}
	
	@Before
	public void initTests() {
		driver = new FirefoxDriver(); 
		usuarios = new UsuariosPage(driver);
	}
	
	@After
	public void endTests() {
		driver.close();
	}
	
	@Test
	public void deveCadastrarUsuario() {
		usuarios.novo().cadastra("edney", "edney@mail.com");
		usuarios.visita();
		
		assertTrue(usuarios.existeNaLista("edney", "edney@mail.com"));
	}

}
