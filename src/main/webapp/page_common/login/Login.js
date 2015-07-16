
  'use strict';
  angular.module('myApp.login',['http-auth-interceptor'])
 .config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'page_common/login/login.html',
    controller: 'LoginController'
  })
}])
  
  
  .controller('LoginController',['$scope','$http','loginService', function ($scope, $http, loginService){      
    $scope.submit = function() {
        
        
        var loginData = {
            login: $scope.login,
            password: $scope.password,
        }
        
        alert(JSON.stringify(loginData));
        loginService.postLoginCred(loginData).success(function(data){
            alert("DataFromServer: " + JSON.stringify(data))
        }).error(function(error){
            alert("login error from server");
        })
        
        //Test Submit
        
        $scope.$emit('hideLoginEvent',loginData);
        
    };
  }])


