package com.edn.JUnitTestSamples.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edn.JUnitTestSamples.model.Lance;
import com.edn.JUnitTestSamples.model.Leilao;

public class Avaliador {

	private double graterThanAll = Double.NEGATIVE_INFINITY;
	private double lesserThanAll = Double.POSITIVE_INFINITY;
	private List<Lance> greaters;

	public void avalia(Leilao leilao) {
		
		if(leilao.getLances().size() == 0)
			throw new RuntimeException("It won't possible evaluate a leilao without at least one lance ");
		
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > graterThanAll)
				graterThanAll = lance.getValor();				
			
			if(lance.getValor() < lesserThanAll)
				lesserThanAll = lance.getValor();
			
			greaters = new ArrayList<>(leilao.getLances());
			Collections.sort(greaters);
			
			greaters = greaters.subList(0, greaters.size() > 3 ? 3 : greaters.size());
		}
	}

	public List<Lance> getTresMaiores() {
		return greaters;
	}
	
	public double getMenorLance() {
		return lesserThanAll;
	}

	public double getMaiorLance() {
		return graterThanAll;
	}

}
