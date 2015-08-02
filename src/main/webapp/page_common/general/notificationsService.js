angular.module('myApp.notifications', [])
.factory('notificationService', ['$http', function($http) {
  var api = {};
  
  api.notification = function(c){
    
    console.log("getting notifictions " + c);
      var url = '/news_controller/unread/' + c;
      return  $http({
        url: url,
        method: 'GET',
        headers: {
          timeout:0
        }
      });
   }
  
  return api;
  

  
}])
//.directive("bounceOnChange",['$animate', function($animate){
//  return function(scope,elem){
//    
//    $animate.setClass(elem,'bounce animated');
//    
//    
//    
//  }
//  
//  
//}
//                        ])
;
  