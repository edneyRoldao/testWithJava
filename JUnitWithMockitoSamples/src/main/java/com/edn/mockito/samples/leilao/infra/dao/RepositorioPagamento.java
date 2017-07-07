package br.com.caelum.leilao.infra.dao;

import br.com.caelum.leilao.dominio.Pagamento;

public interface RepositorioPagamento {

	void salva(Pagamento pagamento);
	
}
