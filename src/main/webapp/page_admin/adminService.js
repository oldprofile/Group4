angular.module('myApp.admin')
.factory('adminService', ['$http', function($http) {
    var adminService = {};
    adminService.getApproveList = function() {
            return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
            .then(function(results) {
                return results.data;
            });     
    };
    
    adminService.getNews = function() {
        return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
        .then(function(results) {
            return results.data;
        });
    };
    
    return adminService;
}]);
