angular.module('myApp.mycourses')
.factory('mycourses', ['$http','userService', function($http,userService) {
    
  return $http.post('user_controller/all_trainings_sorted_by_date',{login:userService.getUser().login} )
            .success(function(data) {
              JSON.stringify(data);
              return data;
            })
            .error(function(err) {
              console.log("Error getting courses");
              return err;
            });
}]);