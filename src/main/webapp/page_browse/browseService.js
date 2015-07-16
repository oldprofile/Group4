angular.module('myApp.browse')
.factory('browseService', ['$http','userService', function($http,userService) {
    
    var browseAPI = {};
    browseAPI.getAllTrainings = $http.get('/training_controller/list')
        .success(function(data) {
              JSON.stringify("get all courses:" + data);
              return data;
            })
            .error(function(err) {
              console.log("Error getting all courses");
              return err;
            });
    
     return browseAPI;
}]);