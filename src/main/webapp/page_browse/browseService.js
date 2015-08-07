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
    
    
    browseAPI.getTrainingsByCategory = function(id){
        //get all
      
        if(id == 0 || id === undefined){
          
            return browseAPI.getAllTrainings();
        }
        
        return ($http.get("training_controller/list_by_category/" + id)
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              console.log("Error getting courses by category: " + id);
              return err;
   }));}
    
    browseAPI.getRecommended = function(){
      return $http.get("training_controller/latest_trainings");
    }
    browseAPI.getFeatured = function(){
      return $http.get("training_controller/featured_trainings");
    }
    
     return browseAPI;
}]);

