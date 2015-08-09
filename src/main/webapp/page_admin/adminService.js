angular.module('myApp.admin')

	.factory('adminService', ['$http', '$q', function ($http, $q) {
		var adminService = {};
		adminService.getApproveList = function () {
			return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
				.then(function (results) {
					return results.data;
				});
		};

		adminService.getNews = function (pageNumber) {
			return $http.get('http://localhost:8080/news_controller/pages/' + pageNumber)
				.then(function (results) {
					return results.data;
				});
		};

		adminService.getNewsNumber = function () {
			return $http.get('http://localhost:8080/news_controller/count_of_news')
				.then(function (results) {
					return results.data;
				});
		};

		adminService.setNewsAsRead = function(id) {
			return $http.get('http://localhost:8080/news_controller/change_unread/' + id)
				.then(function(result) {
					console.log("News state changed successfully");
					return result;
				});
		};

		adminService.sendStatistics = function (statData) {
            debugger;
			var copy = angular.copy(statData);
			copy.type = +copy.type;
			return $http.post('/omission_controller/statistics', copy)
				.then(function (response) {
                    debugger
					adminService.download(response.data);
					return response.data;
				}, function (r) {
					debugger
				});
		};

		adminService.download = function (formData) {
          debugger;
          window.open(formData.dropboxLink, '_blank')
//            debugger;
//			var url = 'http://localhost:8080/' + encodeURI(formData.path);
//			url = url.replace(/%5C/g, "/");
//			var deferred = $q.defer();
//			deferred.resolve(window.open(url, '_blank'));
//			return deferred.promise;
		};

		return adminService;
	}]);
