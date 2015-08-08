angular.module('myApp')
.factory('dateService', function() {
		var dateService = {};

		dateService.isPastDate = function(dateToCompare) {
			var currentDate = new Date();
			var toCompareDate = Date.parse(dateToCompare);
			return (currentDate > toCompareDate) ? true : false;
		}

		return dateService;
	});