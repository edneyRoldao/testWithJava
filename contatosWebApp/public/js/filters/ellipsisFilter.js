angular.module("contatosAppModule").filter("ellipsisFilter", function(/*we can inject services here*/) {

	return function(input, size) {
		if(input.length <= size) return input;
		var output = input.substring(0, (size || 10)) + "...";
		return output;
	};

});