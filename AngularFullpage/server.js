var express = require('express');
var app = express();

// var bodyParser = require('body-parser');
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({extended:true}));
//
// var mongoose = require('mongoose');
// mongoose.connect('mongodb://localhost/vijetWebsite');

//app.use(express.static(__dirname + '/public'));
app.use(express.static(__dirname + '/'));

//
// var server = require("./server/app.js");
// server(app);
app.listen(3000);

