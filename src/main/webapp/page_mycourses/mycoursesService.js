angular.module('myApp.mycourses')
.factory('mycourses', ['$http','userService', function($http,userService) {
    
  return $http.post('user_controller/all_trainings_of_user',{login:userService.getUser().login} )
            .success(function(data) {
              JSON.stringify(data);
              return data;
            })
            .error(function(err) {
              console.log("Error getting courses");
              return err;
            });
}]);