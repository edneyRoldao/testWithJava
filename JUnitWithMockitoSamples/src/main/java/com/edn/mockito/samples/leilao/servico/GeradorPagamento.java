package br.com.caelum.leilao.servico;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.infra.dao.RepositorioLeilao;
import br.com.caelum.leilao.infra.dao.RepositorioPagamento;
import br.com.caelum.leilao.infra.relogio.Relogio;
import br.com.caelum.leilao.infra.relogio.RelogioSistema;

public class GeradorPagamento {

	private final RepositorioLeilao leilaoDao;
	private final RepositorioPagamento pagtoDao;
	private final Avaliador avaliador;
	private final Relogio relogio;
	
	public GeradorPagamento(RepositorioLeilao leilaoDao, Avaliador avaliador, RepositorioPagamento pagtoDao, Relogio relogio) {
		this.leilaoDao = leilaoDao;
		this.avaliador = avaliador;
		this.pagtoDao = pagtoDao;
		this.relogio = relogio;
	}
	
	public GeradorPagamento(RepositorioLeilao leilaoDao, Avaliador avaliador, RepositorioPagamento pagtoDao) {
		this(leilaoDao, avaliador, pagtoDao, new RelogioSistema());
	}
	
	public void gera() {
		List<Leilao> encerrados = leilaoDao.encerrados();
		
		for(Leilao leilao : encerrados) {
			avaliador.avalia(leilao);
			Pagamento pagto = new Pagamento(avaliador.getMaiorLance(), getPrimeiroDiaUtilSemana());
			pagtoDao.salva(pagto);
		}
	}
	
	public Calendar getPrimeiroDiaUtilSemana() {
		Calendar date = relogio.hoje();
		int diaSemana = date.get(Calendar.DAY_OF_WEEK);
		
		if(diaSemana == Calendar.SATURDAY)
			date.add(Calendar.DAY_OF_MONTH, 2);
		
		if(diaSemana == Calendar.SUNDAY)
			date.add(Calendar.DAY_OF_MONTH, 1);
		
		return date;
	}
	
}
