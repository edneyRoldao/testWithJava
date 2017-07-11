angular.module("contatosAppModule").factory("timestampInterceptor", function() {
	return {
		request: function(config) {
			var url = config.url;
			if(url.indexOf('views/') > -1) return config;
			config.url = url + "?timestamp=" + new Date().getTime();
			return config;
		}
	};
});