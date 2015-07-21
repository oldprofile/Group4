angular.module('myApp.admin')
.factory('getToApproveList', ['$http', function($http) {
  return $http.get('http://localhost:8080/training_controller/trainings_for_approve')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);
