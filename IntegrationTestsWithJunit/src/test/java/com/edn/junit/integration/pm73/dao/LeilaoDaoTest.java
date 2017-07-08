package com.edn.junit.integration.pm73.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.edn.junit.integration.pm73.dominio.Leilao;
import com.edn.junit.integration.pm73.dominio.Usuario;

public class LeilaoDaoTest {

	private Session session;
	private LeilaoDao leilaoDao;
	private UsuarioDao usuarioDao;

	@Before
	public void preTests() {
		session = new CriadorDeSessao().getSession();
		leilaoDao = new LeilaoDao(session);
		usuarioDao = new UsuarioDao(session);

		session.beginTransaction();
	}

	@After
	public void posTests() {
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	public void deveContarLeiloesNaoEncerrados() {
		Usuario user = new Usuario("Giselle", "gi@giselle.com");

		Leilao ativo = new Leilao("PSP", 120.00, user, false);
		Leilao encerrado = new Leilao("Game Boy", 35.00, user, false);
		encerrado.encerra();

		usuarioDao.salvar(user);
		leilaoDao.salvar(ativo);
		leilaoDao.salvar(encerrado);

		long total = leilaoDao.total();

		assertThat(total, equalTo(1L));
	}

	@Test
	public void deveRecuperarLeiloesNaoEncerradosNoPeriodo() {
		Calendar init = Calendar.getInstance();
		Calendar fim = Calendar.getInstance();
		init.set(Calendar.DAY_OF_MONTH, -10);

		Usuario user = new Usuario("Giselle", "gi@giselle.com");
		usuarioDao.salvar(user);

		Leilao leilao1 = new Leilao("PSP", 120.00, user, false);
		Calendar dataLeilao1 = Calendar.getInstance();
		dataLeilao1.set(Calendar.DAY_OF_MONTH, -2);
		leilao1.setDataAbertura(dataLeilao1);
		leilaoDao.salvar(leilao1);

		Leilao leilao2 = new Leilao("Game Boy", 35.00, user, false);
		Calendar dataLeilao2 = Calendar.getInstance();
		dataLeilao2.set(Calendar.DAY_OF_MONTH, -20);
		leilao2.setDataAbertura(dataLeilao2);
		leilaoDao.salvar(leilao2);

		List<Leilao> leiloes = leilaoDao.porPeriodo(init, fim);

		assertThat(leiloes.size(), equalTo(1));
		assertThat(leiloes.get(0).getNome(), equalTo("PSP"));
	}

}
