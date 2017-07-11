angular.module("contatosAppModule").directive("dateMaskDirective", function() {
	return {
		require: "ngModel",
		link: function(scope, element, attributes, controller) {

			var _formatDate = function(date) {
				date = date.replace(/[^0-9]+/g, "");
				if(date.length > 2) date = date.substring(0,2) + "/" + date.substring(2);
				if(date.length > 5) date = date.substring(0,5) + "/" + date.substring(5,9);

				return date;				
			};

			element.bind("keyup", function() {
				controller.$setViewValue(_formatDate(controller.$viewValue));
				controller.$render();
			});

			controller.$parsers.push(function(data) {
				if(data.length === 10) {
					var dataToken = data.split("/");
					return new Date(dataToken[2], dataToken[1] - 1, dataToken[0]);
				}				
			});
			
		}
	};
})