package com.edn.mockito.samples.leilao.servico;

import java.util.Calendar;
import java.util.List;

import com.edn.mockito.samples.leilao.dominio.Leilao;
import com.edn.mockito.samples.leilao.infra.dao.Carteiro;
import com.edn.mockito.samples.leilao.infra.dao.RepositorioLeilao;

public class EncerradorDeLeilao {

	private int total = 0;
	private final RepositorioLeilao dao;
	private final Carteiro carteiro;
	
	public EncerradorDeLeilao(RepositorioLeilao dao, Carteiro carteiro) {
		this.dao = dao;
		this.carteiro = carteiro;
	}

	public void encerra() {
		List<Leilao> todosLeiloesCorrentes = dao.correntes();

		for (Leilao leilao : todosLeiloesCorrentes) {
			try {
				if (comecouSemanaPassada(leilao)) {
					leilao.encerra();
					total++;
					dao.atualiza(leilao);
					carteiro.envia(leilao);
				}
			} catch (Exception e) {

			}
		}
		
	}

	private boolean comecouSemanaPassada(Leilao leilao) {
		return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}

		return diasNoIntervalo;
	}

	public int getTotalEncerrados() {
		return total;
	}
}
