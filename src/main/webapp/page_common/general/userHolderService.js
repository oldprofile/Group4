'use strict'


angular.module('myApp').factory('userService',['$http',function ($http) {
  
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
    isLogged: false,  
      
    setUser: function (login,username, password, role_, token) {
      currentUser.login = login;    
      currentUser.username = username;
      currentUser.password = password;
      alert(JSON.stringify(role_));
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
      
     
      
      alert("token: " + token);    
      $http.defaults.headers.common.Authorization = token;  
        
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
      
    logout: function(){
      return $http.post("/authentication/logout",{}).success(function(data){
          this.clearUser();
          this.isLogged = false;
          
          return data;
    }).error(function(err){
          return err;
      })
        
    }  
  };
  return userApi;
}]); 