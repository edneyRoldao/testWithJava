package com.edn.JUnitTestSamples.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.edn.JUnitTestSamples.model.Lance;
import com.edn.JUnitTestSamples.model.Leilao;
import com.edn.JUnitTestSamples.model.Usuario;
import com.edn.JUnitTestSamples.service.LeilaoBuilder;

public class LeilaoTeste {

	private Usuario edy;
	private Usuario gi;
	private Usuario na;
	private Usuario ma;

	@Before
	public void createAvaliador() {
		edy = new Usuario("Edney");
		gi = new Usuario("Giselle");
		na = new Usuario("Nadine");
		ma = new Usuario("Marjorie");
	}

	
	@Test
	public void deveVerificarRecebeUmLance() {
		Leilao leilao = new LeilaoBuilder().para("Macbook pro retina 13").build();
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(na, 7750.00));
		assertEquals(1, leilao.getLances().size());
		assertEquals(7750.00, leilao.getLances().get(0).getValor(), 0000.1);
	}
	
	@Test
	public void deveVerificarValoresLances() {
		Leilao leilao = new LeilaoBuilder()
				.para("Macbook pro retina 13")
				.lance(na, 7750.00)
				.lance(ma, 6895.00)
				.build();
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(7750.00, leilao.getLances().get(0).getValor(), 0000.1);
		assertEquals(6895.00, leilao.getLances().get(1).getValor(), 0000.1);		
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosMesmoUsuario() {
		Leilao leilao= new LeilaoBuilder()
				.para("Macbook pro retina 13")
				.lance(edy, 5550.00)
				.lance(edy, 6000.00)
				.build();

		assertEquals(1, leilao.getLances().size());
		assertEquals(5550.00, leilao.getLances().get(0).getValor(), 0000.1);
	}
	
	@Test
	public void naoDeveAceitarMaisQueCincoLancesParaMesmoUsuarioNoLeilao() {
		Leilao leilao = new LeilaoBuilder()
				.para("Macbook pro retina 13")
				.lance(gi, 2000.00)
				.lance(ma, 2100.00)
				.lance(gi, 2500.00)
				.lance(ma, 2550.00)
				.lance(gi, 3000.00)
				.lance(ma, 3100.00)
				.lance(gi, 3500.00)
				.lance(ma, 3900.00)
				.lance(gi, 4500.00)
				.lance(ma, 5500.00)
				.lance(gi, 6000.00) // This lance must be ignored 
				.build();
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(5500.00, leilao.getLastLance().getValor(), 0000.1);
	}
	
}
























