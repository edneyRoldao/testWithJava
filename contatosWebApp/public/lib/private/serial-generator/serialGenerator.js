angular.module("serialGeneratorService", []);
angular.module("serialGeneratorService").provider("serialGeneratorService", function() {
	var _length = 10;

	this.setLength = function(length) {
		_length = length;
	};

	this.$get = function () {
		return {
			generate: function () {
				var serial = "";
				while(serial.length < _length) {
					serial += String.fromCharCode(Math.floor(Math.random() * 64) + 32);
				}
				return serial;
			}	
		};
	};
});