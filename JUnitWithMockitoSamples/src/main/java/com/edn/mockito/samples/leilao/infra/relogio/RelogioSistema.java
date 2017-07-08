package com.edn.mockito.samples.leilao.infra.relogio;

import java.util.Calendar;

public class RelogioSistema implements Relogio {

	public Calendar hoje() {
		return Calendar.getInstance();
	}

}
