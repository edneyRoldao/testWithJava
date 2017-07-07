package br.com.caelum.leilao.servico;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.Carteiro;
import br.com.caelum.leilao.infra.dao.RepositorioLeilao;

public class EncerradorLeilaoTest {
	
	private RepositorioLeilao dao;
	private Carteiro carteiro;
	
	
	@Before
	public void criaMocks() {
		// Here we have created object mocks from Interfaces but we can use a java class as well.
		dao = mock(RepositorioLeilao.class);
		carteiro = mock(Carteiro.class);
	}

	@Test
	public void deveEncerrarLeiloesIniciadosUmaSemanaAtras() {
		Calendar old = Calendar.getInstance();
		old.set(2002, 11, 17);
		
		Leilao l = new CriadorDeLeilao().para("Sony Bravia").naData(old).constroi();
		Leilao l2 = new CriadorDeLeilao().para("Eletric Brush").naData(old).constroi();
		List<Leilao> leiloes = Arrays.asList(l, l2);
		
		when(dao.correntes()).thenReturn(leiloes);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(dao, carteiro);
		encerrador.encerra();
		
		assertEquals(2, encerrador.getTotalEncerrados());
		assertTrue(l.isEncerrado());
		assertTrue(l2.isEncerrado());		
	}
	
	
	@Test
	public void deveChamarMetodoAtualizaNoDao() {
		Calendar old = Calendar.getInstance();
		old.set(2015, 02, 15);
		
		Leilao leilao = new CriadorDeLeilao().para("TV sansumg").naData(old).constroi();

		when(dao.correntes()).thenReturn(Arrays.asList(leilao));
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(dao, carteiro);
		encerrador.encerra();
		
		verify(dao, times(1)).atualiza(leilao);
	}
	
	
	
	
	@Test
	public void deveContinuarExecutandoMesmoLancandoException() {
		Calendar old = Calendar.getInstance();
		old.set(2002, 11, 17);
		
		Leilao leilao = new CriadorDeLeilao().para("Sony Bravia").naData(old).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Eletric Brush").naData(old).constroi();
		
		when(dao.correntes()).thenReturn(Arrays.asList(leilao, leilao2));
		doThrow(new RuntimeException()).when(dao).atualiza(leilao);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(dao, carteiro);
		encerrador.encerra();

		verify(dao).atualiza(leilao2);
		verify(carteiro).envia(leilao2);
		
		verify(carteiro, times(0)).envia(leilao);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
