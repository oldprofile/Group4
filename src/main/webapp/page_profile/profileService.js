angular.module('myApp.profile')
.factory('getFeedbacksOnUserService', ['$http', function($http) {
  return $http.get('http://localhost:8080/allCategories' + user.login)
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);