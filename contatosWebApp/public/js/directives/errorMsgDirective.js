angular.module("contatosAppModule").directive("uiErroMsg", function() {
	return {
		templateUrl: "views/uiRestErrorMsg.html",
		replace: true,
		restrict: "AE",
		scope: {
			topic: "@title",
			messageValue: "=message"
		},
		transclude: true
	};
});