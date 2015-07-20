angular.module('myApp.login')
.factory('loginService',['$http','authService', function($http, authService) {
    
    var loginService = {};
    
    loginService.postLoginCred = function(logindata){
        return $http.post('/authentication/log_password',logindata).success(function(data, status, headers) {
              var token = headers("token");
              data.token = token;
              authService.loginConfirmed(data);
              return data;
            })
            .error(function(err) {
              return err;
            }) 
    }
    
  return loginService
  }
]);
