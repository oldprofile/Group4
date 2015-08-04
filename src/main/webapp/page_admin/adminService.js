angular.module('myApp.admin')
.factory('adminService', ['$http', function($http) {
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
				debugger
				alert(response.status);
				//adminService.download(results.data);
				return response.data;
			}, function (r) {debugger});
	};

		/*adminService.download = function (formData) {
		 var url = 'file:///C:/Users/Yoga%203%20Pro/My%20programs/exadel/Group4/src/main/webapp/' + encodeURIComponent(JSON.stringify(formData));
		 var deferred = $q.defer();
		 deferred.resolve(window.open(url, '_blank'));
		 return deferred.promise;
		 };*/
    
    return adminService;
}]);
