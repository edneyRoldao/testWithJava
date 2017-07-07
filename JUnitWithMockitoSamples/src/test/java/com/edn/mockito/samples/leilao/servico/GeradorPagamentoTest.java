package br.com.caelum.leilao.servico;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.infra.dao.RepositorioLeilao;
import br.com.caelum.leilao.infra.dao.RepositorioPagamento;
import br.com.caelum.leilao.infra.relogio.Relogio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Calendar;

public class GeradorPagamentoTest {

	private RepositorioLeilao leilaoDao;
	private RepositorioPagamento pagamentoDao;
	private Avaliador avaliador;
	private Relogio relogio;

	@Before
	public void criaMocks() {
		this.leilaoDao = mock(RepositorioLeilao.class);
		this.pagamentoDao = mock(RepositorioPagamento.class);
		this.avaliador = mock(Avaliador.class);
		this.relogio = mock(Relogio.class);
	}

	@Test
	public void deveGerarPagamentoParaLeilaoEncerrado() {
		Leilao leilao = new CriadorDeLeilao().para("DreamCast").lance(new Usuario("Edney"), 750.00)
				.lance(new Usuario("Nadine"), 800.00).constroi();

		when(leilaoDao.encerrados()).thenReturn(Arrays.asList(leilao));
		when(avaliador.getMaiorLance()).thenReturn(800.00);

		GeradorPagamento gerador = new GeradorPagamento(leilaoDao, avaliador, pagamentoDao);

		// To understand how ArgumentCaptor works, let's take a look carefully
		// to the next peace of code:

		gerador.gera(); // this method will create an object Pagamento that will
						// be passed as argument to pagamentoDao

		// ArgumentCapture provide us a such way to retrieve arguments from mock
		// objects
		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);

		// Verify will make sure that the method salva will be called and aside
		// from that it take the current argument by using ArgumentCaptor
		verify(pagamentoDao).salva(argumento.capture());

		// Here we have the object retrieved from ArgumentCaptor
		Pagamento pagto = argumento.getValue();

		assertThat(pagto.getValor(), equalTo(800.00));
	}

	@Test
	public void deveLevarProximoDiaUtilQuandoFimDeSemana() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Sony vaio")
				.lance(new Usuario("Giselle"), 3200.00)
				.lance(new Usuario("Marjorie"), 2100.00)
				.constroi();

		when(leilaoDao.encerrados()).thenReturn(Arrays.asList(leilao));
		
		Calendar sabado = Calendar.getInstance();
		sabado.set(2012, Calendar.APRIL, 7);
		
		when(relogio.hoje()).thenReturn(sabado);

		GeradorPagamento gerador = new GeradorPagamento(leilaoDao, new Avaliador(), pagamentoDao, relogio);
		gerador.gera();
		
		ArgumentCaptor<Pagamento> pagamento = ArgumentCaptor.forClass(Pagamento.class);
		verify(pagamentoDao).salva(pagamento.capture());
		
		Pagamento pagtoGerado = pagamento.getValue();
		
		assertThat(pagtoGerado.getData().get(Calendar.DAY_OF_WEEK), equalTo(Calendar.MONDAY));
	}

}
