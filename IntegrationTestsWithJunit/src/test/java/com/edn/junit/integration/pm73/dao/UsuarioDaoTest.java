package com.edn.junit.integration.pm73.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.edn.junit.integration.pm73.dominio.Usuario;

public class UsuarioDaoTest {

	private UsuarioDao dao;
	private Session session;

	@Before
	public void preTests() {
		session = new CriadorDeSessao().getSession();
		dao = new UsuarioDao(session);

		session.beginTransaction();
	}

	@After
	public void posTests() {
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	public void deveBuscarPorNameAndEmail() {
		dao.salvar(new Usuario("edney", "edneyroldao@mail.com"));

		Usuario user = dao.porNomeEEmail("edney", "edneyroldao@mail.com");

		assertThat(user.getNome(), equalTo("edney"));
		assertThat(user.getEmail(), equalTo("edneyroldao@mail.com"));
	}

	@Test
	public void deveRetornarNullQuandoUsuarioNaoExistir() {
		Usuario user = new Usuario("Marjorie", "nadine@mail.com");
		assertNull(dao.porNomeEEmail(user.getNome(), user.getEmail()));
	}

	@Test
	public void deveDeletarUsuario() {
		Usuario user = new Usuario("Nadine Roldao", "nadine@mail.com");

		dao.salvar(user);
		dao.deletar(user);

		// Frameworks like Hibernate store each SQL command and execute all together at
		// the end of transaction
		// fortunately, we can force those operations by following command and make sure
		// the tests will pass.
		session.flush();
		session.clear();

		user = dao.porNomeEEmail(user.getNome(), user.getEmail());

		assertNull(user);
	}

}
