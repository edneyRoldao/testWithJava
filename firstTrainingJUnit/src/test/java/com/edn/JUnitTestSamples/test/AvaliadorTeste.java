package com.edn.JUnitTestSamples.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.edn.JUnitTestSamples.model.Lance;
import com.edn.JUnitTestSamples.model.Leilao;
import com.edn.JUnitTestSamples.model.Usuario;
import com.edn.JUnitTestSamples.service.Avaliador;
import com.edn.JUnitTestSamples.service.LeilaoBuilder;

public class AvaliadorTeste {
	
	private Avaliador avaliador;
	private Usuario edy;
	private Usuario gi;
	private Usuario na;
	private Usuario ma;
	
	
	@Before
	public void createAvaliador() {
		avaliador = new Avaliador();
		edy = new Usuario("Edney");
		gi = new Usuario("Giselle");
		na = new Usuario("Nadine");
		ma = new Usuario("Marjorie");
	}

	@Test
	public void deveVerificarMenorEMaiorLance() {
		Leilao leilao = new LeilaoBuilder()
				.para("new Playstation 4")
				.lance(edy, 250.00)
				.lance(gi, 300.00)
				.lance(na, 400.00)
				.lance(ma, 900.00)
				.build();
		
		avaliador.avalia(leilao);

		assertThat(avaliador.getMaiorLance(), equalTo(900.00));
		assertThat(avaliador.getMenorLance(), equalTo(250.00));
	}

	@Test
	public void deveVerificarLeilaoComApenasUmLance() {
		Leilao leilao = new LeilaoBuilder()
				.para("new Playstation 4")
				.lance(edy, 250.00)
				.build();

		avaliador.avalia(leilao);
		
		assertThat(avaliador.getMenorLance(), equalTo(250.00));
		assertThat(avaliador.getMaiorLance(), equalTo(250.00));
	}
	
	@Test
	public void deveVerificarOsTresMaioresLances() {
		Leilao leilao = new LeilaoBuilder()
				.para("new Playstation 4")
				.lance(edy, 250.00)
				.lance(gi, 1200.00)
				.lance(na, 400.00)
				.lance(ma, 745.00)
				.lance(edy, 1500.00)
				.lance(gi, 300.00)
				.lance(na, 950.00)
				.lance(ma, 900.00)
				.build();

		avaliador.avalia(leilao);
		
		List<Lance> lancesMaiores = avaliador.getTresMaiores();

		assertThat(lancesMaiores.size(), equalTo(3));
		
		assertThat(lancesMaiores, hasItems(
					new Lance(edy, 1500.00),
					new Lance(gi, 1200.00),
					new Lance(na, 950.00)
				));		
	}
	
	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemLances() {
		Leilao leilao = new LeilaoBuilder()
				.para("Sony VAIO SVS15125cbb")
				.build();
		
		avaliador.avalia(leilao);
	}

	
	
}
