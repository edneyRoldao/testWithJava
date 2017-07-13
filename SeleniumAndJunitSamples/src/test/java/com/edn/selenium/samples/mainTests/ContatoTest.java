package com.edn.selenium.samples.mainTests;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContatoTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private ContatosListPage contatosPage;

	@BeforeClass
	public static void load() {
		System.setProperty("webdriver.gecko.driver", "C:\\workspace\\geckodriver\\geckodriver.exe");
	}

	@Before
	public void init() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 1);
		contatosPage = new ContatosListPage(driver, wait);
	}

	@After
	public void endTests() {
		driver.close();
	}

	@Test
	public void deveAdicionarNovoContato() {
		contatosPage.visit();
		contatosPage.newContato().createNewUser("Elaine Ap Roldao", "9876-5432", "12/12/2017", "Vivo");

		assertTrue(contatosPage.isThereOnList("Elaine Ap Roldao", "9876-5432", "12/12/2017", "Vivo"));
	}

}
