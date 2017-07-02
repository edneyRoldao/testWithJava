package com.edn.JUnitTestSamples.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {
		if (isNotTheSameUserFromLastLance(lance.getUsuario()) && isUserWithLessThanFiveLances(lance.getUsuario()))
			lances.add(lance);
	}
	
	public Lance getLastLance() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	private boolean isNotTheSameUserFromLastLance(Usuario usuario) {
		return (lances.isEmpty() || !getUltimoLance().getUsuario().equals(usuario));
	}

	private Lance getUltimoLance() {
		return lances.get(lances.size() - 1);
	}

	private boolean isUserWithLessThanFiveLances(Usuario usuario) {
		int total = 0;

		for(Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)) total++;
		}
		
		return total < 5;
	}
	
}
