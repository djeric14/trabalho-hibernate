(function() {
	'use strict';

	angular
		.module('sellerApp', ['ngMaterial', 'ngRoute', 'uiGmapgoogle-maps'])
		.config(Config);

	function Config($routeProvider, $locationProvider) {
	  $routeProvider
	  	.when('/', {templateUrl: '/assets/views/main.html', controller: 'MainCtrl'})
	  	.when('/vendedor/list', {templateUrl: '/assets/views/vendedor/list.html', controller: 'VendedorListCtrl'})
	  	.when('/vendedor/edit/:id', {templateUrl: '/assets/views/vendedor/edit.html', controller: 'VendedorEditCtrl'});
	}

})();

