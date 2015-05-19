(function() {
	'use strict';

	angular
		.module('sellerApp')
		.controller('VendedorEditCtrl', VendedorEditCtrl);

	function VendedorEditCtrl($scope, $route) {
		$scope.id = $route.current.params.id;
	}
})();

