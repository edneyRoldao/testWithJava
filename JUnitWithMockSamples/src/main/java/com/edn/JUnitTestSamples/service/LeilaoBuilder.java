package com.edn.JUnitTestSamples.service;

import com.edn.JUnitTestSamples.model.Lance;
import com.edn.JUnitTestSamples.model.Leilao;
import com.edn.JUnitTestSamples.model.Usuario;

public class LeilaoBuilder {

	private Leilao leilao;

	public LeilaoBuilder para(String descricao) {
		leilao = new Leilao(descricao);
		return this;
	}

	public LeilaoBuilder lance(Usuario user, double valor) {
		leilao.propoe(new Lance(user, valor));
		return this;
	}

	public Leilao build() {
		return leilao;
	}
}
