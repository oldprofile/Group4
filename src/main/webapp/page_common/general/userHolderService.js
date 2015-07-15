'use strict'


angular.module('myApp').factory('userService', ['$http',function ($http) {
  
  var role = {
   user: {
       id: 2,
       string: "user"
   },
    admin: {
      id: 1,
      string: "admin"
    },
    extCoach : {
        id:3,
        string: "externalCoach"    
    }   
  }    
    
  var currentUser =  {
    login: 'yo_login',  
    username: 'yo',
    password: 'yo',
    role: role.admin,  
  }

  var userApi = {
    setUser: function (login,username, password, role) {
      currentUser.login = login;    
      currentUser.username = username;
      currentUser.password = password;
        
      $http.defaults.headers.common.Authorization = login;    
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