package com.edn.mockito.samples.leilao.dominio;

public class ClassePrincipal {

	String descricao = "sua a classe principal";	

	// CLASSE ANINHADA
	class ClasseAninhada {
		String descricao = "sou a classe aninhada";
		
		void getDescricao() {
			System.out.println(this.descricao); // classe aninhada
			System.out.println(ClassePrincipal.this.descricao); // referencia classe principal
		}
		
	}
}
