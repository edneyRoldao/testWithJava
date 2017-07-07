package br.com.caelum.leilao.infra.dao;

import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;

public interface RepositorioLeilao {
	
	void salva(Leilao leilao);
	
	List<Leilao> encerrados();
	
	List<Leilao> correntes();
	
	void atualiza(Leilao leilao);

}
