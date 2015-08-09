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

		$scope.getNewsByType = function(type, news) {
			switch(type) {
				case 0:
					return news.training.trainingName;
				break;
				case 1:
					return news.coachFeedbackGETModel.coachLogin;
				break;
				case 2:
					return news.trainingFeedbackGETModel.trainingName;
				break;
				case 3:
					return news.userFeedbackGETModel.userLogin;
				break;
				default:
					console.log('error, unknown type');
			}
		};

		$scope.getLinkByType = function(type, news) {
			switch(type) {
				case 0:
					return '#/courseinfo/' + news.training.trainingName;
					break;
				case 1:
					return '#/profile/' + news.coachFeedbackGETModel.coachLogin;
					break;
				case 2:
					return '#/courseinfo/' + news.trainingFeedbackGETModel.trainingName;
					break;
				case 3:
					return '#/profile/' + news.userFeedbackGETModel.userLogin;
					break;
				default:
					console.log('error, unknown type');
			}
		};

		$scope.setAsRead = function(id) {
			adminService.setNewsAsRead(id).then(function(result) {
					$scope.getNews($scope.pageNumber);
				}
			);
		};

		$scope.getNewsNumber();

		$scope.getNews($scope.pageNumber);
	}]);