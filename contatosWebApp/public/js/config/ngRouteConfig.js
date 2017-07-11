angular.module("contatosAppModule").config(function($routeProvider) {
	$routeProvider.when("/contatos", {
		templateUrl: "views/contatos.html",
		controller: "contatosController"
	});

	$routeProvider.when("/novoContato", {
		templateUrl: "views/newContato.html",
		controller: "newContatoControlle",
		resolve: {
			operadoras: function(operadorasAPIService) {
				return operadorasAPIService.getOperadoras();
			}
		}
	});

	$routeProvider.when("/detalhesContato/:id", {
		templateUrl: "views/detalhesContato.html",
		controller: "detalhesContatoController",
		resolve: {
			contato: function(contatosAPIService, $route) {
				return contatosAPIService.getContato($route.current.params.id);
			}
		}
	});

	$routeProvider.when("/error", {
		templateUrl: "views/error.html"
	});

	$routeProvider.otherwise({redirectTo: "/contatos"});
});