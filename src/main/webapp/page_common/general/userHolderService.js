'use strict'


angular.module('myApp').factory('userService',['$http','loginService','$window','$q',function ($http,loginService,$window,$q) {
  var pr = $q.defer();
  
  var role = {
   admin: {
      id: 1,
      string: "Admin"
    },      
   user: {
       id: 2,
       string: "Employee"
   },
    exCoach : {
        id:3,
        string: "ExCoach"    
    }, 
    exEmployee : {
        id:4,
        string: "exEmployee"    
    }  
  }    
    
  var currentUser =  {
    login: 'yo_login',  
    role: role.admin,
    token: "",
  }

  var userApi = {
    isLogged: false,  
      
    setUser: function (login,role_, token) {
      
      currentUser.login = login; 
      pr.resolve(login);
      currentUser.token = token;
      
      
      
      var nrole = role_.reduce(function(max,cur){
        if(max.id < cur.id){
          return max;
        } else {
          return cur;
        }
      });
      
      for (var r in role){
        if(role[r].id === nrole.id){  
          currentUser.role =  role[r];
        }
      }
      
     
      
       
      $http.defaults.headers.common.Authorization = token;
      $http.defaults.headers.common.Login = login;  
        
      this.isLogged = true;
        
        
    },
    getUser: function () {
      return angular.copy(currentUser);
    },
    clearUser: function () {
      currentUser = {};
    },
    
    isAdmin: function(){
      return (currentUser.role === role.admin);
    },
    
    loginPromise: function(){
      return pr.promise;
    },
      
    logout: function(){
      var userService = this;
      
      return $http.post("/authentication/logout",{}).finally(function(){
          userService.clearUser();
          loginService.clearCreds();
          userService.isLogged = false;
          $window.location.reload();
          
      });
        
    }  
  };
  return userApi;
}]);