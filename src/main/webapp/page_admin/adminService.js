angular.module('myApp.admin')
.factory('approveService', ['$http', function($http) {
    return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
        .success(function(data) {
            toApproveList = angular.copy(data);
    })
        .error(function(err) {
            return err;
    });
}]);
