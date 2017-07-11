var contatos = [];

contatos.push({
	id: "01",
	nome: "edney",
	data: new Date(),
	serial: "kfidtf",
	selected: false,
	telefone: "2222-3333",
	operadora: {nome: "Tim"}
});

contatos.push({
	id: "02",
	nome: "Nadine",
	data: new Date(),
	serial: "lskdi",
	selected: false,
	telefone: "4444-5555",
	operadora: {nome: "Vivo"}
});

contatos.push({
	id: "03",
	nome: "Giselle",
	data: new Date(),
	serial: "kfffge",
	selected: false,
	telefone: "7777-8888",
	operadora: {nome: "Claro"}
});

contatos.push({
	id: "04",
	nome: "Marjorie",
	data: new Date(),
	serial: "mlzac",
	selected: false,
	telefone: "1111-0000",
	operadora: {nome: "Oi"}
});

angular.module("contatosAppModule").factory("contatosAPIService", function() {
	var service = {};

	service.getContatos = function() {
		return contatos;
	};

	service.saveContato = function(contato) {
		contatos.push(contato);
	};

	service.getContato = function(id) {
		return contatos.filter(function(contato) {
			return contato.id = id;
		})[0];
	};

	return service;
});
