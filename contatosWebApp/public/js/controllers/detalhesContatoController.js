angular.module("contatosAppModule").controller("detalhesContatoController", function($scope, contato) {
	$scope.app = "Detalhes Contato";
	$scope.contato = contato;
});