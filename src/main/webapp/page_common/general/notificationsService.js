angular.module('myApp.notifications', [])
.factory('notificationService', ['$http', function($http) {
  var api = {};
  api.notification = function(c){
    
<<<<<<< Updated upstream
      return $http.get("/news_controller/unread/"+c);
   }
  
  return api;
  
=======
      return $http.post("",c);
   }
  
>>>>>>> Stashed changes
  
}])
  