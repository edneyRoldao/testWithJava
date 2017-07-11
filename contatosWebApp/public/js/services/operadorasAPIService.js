var operadoras = [];

operadoras.push({nome: "Tim"});
operadoras.push({nome: "Vivo"});
operadoras.push({nome: "Oi"});
operadoras.push({nome: "Claro"});

angular.module("contatosAppModule").service("operadorasAPIService", function() {
	this.getOperadoras = function() {
		return operadoras;
	};
});
