(function() {
	'use strict';

	angular
		.module('sellerApp')
		.controller('MainCtrl', MainCtrl);

	function MainCtrl($scope, $mdSidenav) {
		$scope.toggleSidenav = function(menuId) {
	    	$mdSidenav(menuId).toggle();
	  	};

	  	$scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };
	}
})();

