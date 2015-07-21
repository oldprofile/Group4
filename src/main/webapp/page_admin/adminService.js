angular.module('myApp.admin')
.factory('getToApproveList', ['$http', function($http) {
  return $http.get('http://localhost:8080/allCategories')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);
