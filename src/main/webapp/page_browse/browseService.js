angular.module('myApp.browse')
.factory('browseService', ['$http', function($http) {
    
    var browseAPI = {};
    browseAPI.getAllTrainings = function(){
        return ($http.get('training_controller/list')
            .success(function(data) {
            
              return data;
            })
            .error(function(err) {
              console.log("Error getting all courses");
              return err;
            }));}
    
     return browseAPI;
}]);

