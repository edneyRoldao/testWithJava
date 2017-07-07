package br.com.caelum.leilao.infra.dao;

import br.com.caelum.leilao.dominio.Leilao;

public interface Carteiro {

	void envia(Leilao leilao);
	
}
