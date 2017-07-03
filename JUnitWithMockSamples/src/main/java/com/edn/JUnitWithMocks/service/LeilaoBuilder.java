package com.edn.JUnitWithMocks.service;

import com.edn.JUnitWithMocks.model.Lance;
import com.edn.JUnitWithMocks.model.Leilao;
import com.edn.JUnitWithMocks.model.Usuario;

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
