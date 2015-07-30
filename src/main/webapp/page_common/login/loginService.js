angular.module('myApp.login')
.factory('loginService',['$http','authService', '$cookies', function($http, authService, $cookies) {
    
    var loginService = {};
    
    loginService.postLoginCred = function(logindata){
        return $http.post('/authentication/log_password',logindata);
    }
    
    loginService.setCreds = function(token,login,role){
      $cookies.put("token",token);
      $cookies.put("login",login);
      $cookies.putObject("role",role); 
    }
    
    loginService.getCreds = function(){
      var creds = $cookies.getAll();
      creds.role = $cookies.getObject("role");
      
      return creds;
    }
    
    loginService.checkCreds = function(){
      creds = $cookies.getAll();
      //alert(JSON.stringify(creds));
      if(creds.token !== undefined && creds.token !== "" 
        && creds.login !== undefined && creds.login !== ""){
        return true;
      }
      return false;
    }
    loginService.clearCreds = function(){
      $cookies.remove("token");
      $cookies.remove("login");
      $cookies.remove("role");
    }
    
  return loginService
  }
]);
