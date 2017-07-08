package com.edn.junit.integration.pm73.curso;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.edn.junit.integration.pm73.dao.CriadorDeSessao;

public class CriaTabelas {

	public static void main(String[] args) {
		
		Configuration cfg = new CriadorDeSessao().getConfig();
		SchemaExport se = new SchemaExport(cfg);
		
		se.create(true, true);
	}
	
}
