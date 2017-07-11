var http = require('http');
var app = require('./config/express')();

http.createServer(app).listen(app.get('port'), function() {
	console.log('Express server is working now from port: ' + app.get('port'));
});
