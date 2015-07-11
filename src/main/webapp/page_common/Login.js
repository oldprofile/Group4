(function() {
  'use strict';
  angular.module('myApp.login',['http-auth-interceptor'])
  
  .controller('LoginController', function ($scope, $http, authService)      {
    $scope.submit = function() {
//      $http.post('auth/login').success(function() {
//        authService.loginConfirmed();
//      });
        alert("login: " + $scope.username + " password: " + $scope.password);
        $scope.$emit('hideLoginEvent','hi');
        
    };
  });
})();
