angular.module('myApp.mycourses')
.factory('mycourses', ['$http', function($http) {
  return $http.get('assets/mycourses_test.json')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);