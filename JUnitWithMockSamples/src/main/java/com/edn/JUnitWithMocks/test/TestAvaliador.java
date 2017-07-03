package com.edn.JUnitWithMocks.test;

import com.edn.JUnitWithMocks.model.Lance;
import com.edn.JUnitWithMocks.model.Leilao;
import com.edn.JUnitWithMocks.model.Usuario;
import com.edn.JUnitWithMocks.service.Avaliador;

public class TestAvaliador {

	public static void main(String[] args) {
		
		Usuario edy = new Usuario("Edney");
		Usuario gi = new Usuario("Giselle");
		Usuario na = new Usuario("Nadine");
		Usuario ma = new Usuario("Marjorie");
		
		Leilao leilao = new Leilao("new Playstation 4");
		
		leilao.propoe(new Lance(edy, 250.00));
		leilao.propoe(new Lance(gi, 300.00));
		leilao.propoe(new Lance(na, 400.00));
		leilao.propoe(new Lance(ma, 400.00));
		
		Avaliador avaliador = new Avaliador();
		
		avaliador.avalia(leilao);
		
		System.out.println(avaliador.getMaiorLance());
		System.out.println(avaliador.getMenorLance());
	}
	
}
