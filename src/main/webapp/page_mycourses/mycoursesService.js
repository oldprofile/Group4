angular.module('myApp.mycourses')
.factory('mycourses', ['$http','userService', function($http,userService) {
    
    var data = {
        login: userService.getUser().login,
        state: [2,3],
    }
    
  return $http.post('user_controller/all_trainings_sorted_by_date', data)
            .success(function(data) {
              JSON.stringify(data);
              return data;
            })
            .error(function(err) {
              console.log("Error getting courses");
              return err;
            });
}]);