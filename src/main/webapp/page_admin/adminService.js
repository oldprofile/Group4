angular.module('myApp.admin')
.factory('approveService', ['$http', function($http) {
    var approveService = {};
    approveService.getApproveList = function() {
            return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
            .then(function(results) {
                return results.data;
            });     
    };
    return approveService;                          
}])
.factory('newsService', ['$http', function($http) {
    var newsService = {};
    
    newsService.getNews = function() {
        return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
        .then(function(results) {
            return results.data;
        });
    };
    
    return newsService;
}]);
