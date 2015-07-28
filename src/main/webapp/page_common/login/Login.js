
  'use strict';
  angular.module('myApp.login',['http-auth-interceptor','ngCookies'])
 .config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'page_common/login/login.html',
    controller: 'LoginController'
  })
}])
  
  
  .controller('LoginController',['$scope','$http','loginService', 'authService', function ($scope, $http, loginService,authService){      
    $scope.submit = function() {
        
        
        var loginData = {
            login: $scope.login,
            password: $scope.password,
        }
        
        
        loginService.postLoginCred(loginData).success(function(data,status,headers){
          if($scope.isRemember){
            
            var token = headers("token");
            data.token = token;
            if($scope.isRemember){
              loginService.setCreds(data.token,data.login,data.role);
            }
            
            authService.loginConfirmed(data);
            
            
            
          }
          
          
        }).error(function(error){
            alert("login error from server");
            $scope.$emit('hideLoginEvent',loginData);
        })
        
        //Test Submit
        
        
        
    };
  }])


