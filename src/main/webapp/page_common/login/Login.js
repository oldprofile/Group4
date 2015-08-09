
  'use strict';
  angular.module('myApp.login',['http-auth-interceptor','ngCookies'])
 .config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'page_common/login/login.html',
    controller: 'LoginController'
  })
}])
  
  
  .controller('LoginController',['$scope','$http','loginService', 'authService', function ($scope, $http, loginService,authService){   
    
    $scope.loginError = false;
    $scope.submit = function() {
        
        
        var loginData = {
            login: $scope.login,
            password: $scope.password,
        }
        
        
        loginService.postLoginCred(loginData).success(function(data,status,headers){
          
            
            var token = headers("token");
            data.token = token;
            if($scope.isRemember){
              loginService.setCreds(data.token,data.login,data.role);
            }
            
            authService.loginConfirmed(data);
            
          
        }).error(function(error){
          
            $scope.loginError = !$scope.loginError;
            
        });
        
        //Test Submit
        
        
        
    };
  }])


