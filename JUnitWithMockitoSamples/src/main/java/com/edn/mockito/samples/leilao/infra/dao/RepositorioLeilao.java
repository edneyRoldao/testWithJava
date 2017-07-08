package com.edn.mockito.samples.leilao.infra.dao;

import java.util.List;

import com.edn.mockito.samples.leilao.dominio.Leilao;

public interface RepositorioLeilao {
	
	void salva(Leilao leilao);
	
	List<Leilao> encerrados();
	
	List<Leilao> correntes();
	
	void atualiza(Leilao leilao);

}
