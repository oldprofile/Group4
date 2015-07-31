angular.module('myApp.notifications', [])
.factory('notificationService', ['$http', function($http) {
  var api = {};
  api.notification = function(c){
    
      return $http.get("/news_controller/c");
   }
  
  return api;
  
  
}])
  