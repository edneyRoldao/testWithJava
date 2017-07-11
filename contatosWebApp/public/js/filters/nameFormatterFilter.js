angular.module("contatosAppModule").filter("nameFormatFilter", function(/*we can inject services here*/) {

	return function(input) {
		var nameToken = input.split(" ");
		var regex = /(da|de|do|dos|das)/gi;

		var nameFormatted = nameToken.map(function(name) {
			if(name.length < 4 && regex.test(name)) return name.toLowerCase();
			return name.charAt(0).toUpperCase() + name.substring(1).toLowerCase();
		});

		return nameFormatted.join(" ");			
	};

});