package com.edn.mockito.samples.leilao.infra.dao;

import com.edn.mockito.samples.leilao.dominio.Pagamento;

public interface RepositorioPagamento {

	void salva(Pagamento pagamento);

}
