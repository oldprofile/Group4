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
		return $http.post('/omission_controller/test', statData)
			.then(function(results) {
				alert(results.status);
				return results.data;
			});
	};
    
    return adminService;
}]);
