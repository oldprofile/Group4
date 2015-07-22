'use strict'


angular.module('myApp').factory('userService', ['$http',function ($http) {
  
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
    username: 'yo',
    password: 'yo',
    role: role.admin,  
  }

  var userApi = {
    setUser: function (login,username, password, role_, token) {
      currentUser.login = login;    
      currentUser.username = username;
      currentUser.password = password;
      
      currentUser.role = role.admin;
        
        
      alert("token: " + token);    
      $http.defaults.headers.common.Authorization = token;    
        
    },
    getUser: function () {
      return angular.copy(currentUser);
    },
    clearUser: function () {
      currentUser = {};
    }
  };
  return userApi;
}]);