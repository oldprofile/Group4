angular.module('myApp.admin')
	.controller('NewsController', ['$scope', 'adminService', function ($scope, adminService) {
		$scope.newsList = [];
		$scope.maxSize = 5;
		$scope.totalItems = 0;
		$scope.pageNumber = 1;

		$scope.getNews = function (pageNumber) {
			adminService.getNews(pageNumber).then(function (data) {
				$scope.newsList = data;
				console.log($scope.newsList);
			});
		};

		$scope.getNewsNumber = function() {
			adminService.getNewsNumber().then(function(data) {
				$scope.totalItems = data;
			})
		}

		$scope.getNewsNumber();

		$scope.getNews($scope.pageNumber);
	}]);