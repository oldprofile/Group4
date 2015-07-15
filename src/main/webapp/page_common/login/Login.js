
  'use strict';
  angular.module('myApp.login',['http-auth-interceptor'])
  
  .controller('LoginController',['$scope','$http','loginService', function ($scope, $http, loginService){      
    $scope.submit = function() {
        
        
        var loginData = {
            login: $scope.login,
            password: $scope.password,
        }
        
        alert(JSON.stringify(loginData));
        loginService.postLoginCred(loginData).success(function(data){
            alert(JSON.stringify(data))
        }).error(function(error){
            alert("login error from server");
        })
        
        //Test Submit
        alert("login: " + $scope.login + " password: " + $scope.password);
        $scope.$emit('hideLoginEvent',loginData);
        
    };
  }])
   .directive('loginCard',function(){
        return {
            restrict: "E",
            templateUrl: "page_common/login/login.html"
        };
    });

