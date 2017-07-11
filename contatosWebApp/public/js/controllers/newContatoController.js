angular.module("contatosAppModule").controller("newContatoControlle", function($scope, $location, operadoras, contatosAPIService, serialGeneratorService) {
	$scope.app = "Novo Contato";
	$scope.operadoras = operadoras;

	$scope.add = function(contato) {
		contato.serial = serialGeneratorService.generate();
		contatosAPIService.saveContato(contato);
		delete $scope.contato;
		$scope.contatoForm.$setPristine();
		$location.path("/contatos");
	};

});