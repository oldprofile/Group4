angular.module('myApp.admin')
.factory('adminService', ['$http', '$q', function($http, $q) {
    var adminService = {};
    adminService.getApproveList = function() {
            return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
            .then(function(results) {
                return results.data;
            });     
    };
    
    adminService.getNews = function(pageNumber) {
        return $http.get('http://localhost:8080/news_controller/pages/' + pageNumber)
        .then(function(results) {
            return results.data;
        });
    };

	adminService.sendStatistics = function(statData) {
		return $http.post('/omission_controller/statistics', angular.copy(statData))
			.then(function(response) {
				alert(response.status);
				alert(response.data.path);
				adminService.download(response.data);
				return response.data;
			}, function (r) {debugger});
	};

		adminService.download = function (formData) {
		 var url = 'file:///' + encodeURI(formData.path);
		 url = url.replace(/%5C/g, "/");
		 alert(url);
		 var deferred = $q.defer();
		 deferred.resolve(window.open(url, '_blank'));
		 return deferred.promise;
		 };
    
    return adminService;
}]);
