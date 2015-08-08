angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', "$location", function($scope, mycourses,$location) {

    $scope.dosFilter = {search:"",
                       qType: "All"}
    $scope.isNoCourses = false;
    $scope.mycourses = [];
    $scope.isContentLoaded = false;
    
    $scope.showDosHeadTool = function(){
      var s = $scope.isContentLoaded && !$scope.isNoCourses;
      return $scope.isContentLoaded && !$scope.isNoCourses;
      
    }
    
  
    $scope.createcourse = function(){
      $location.path("/createcourse");
    }
    mycourses.getAllActualUserCourses().success(function(data,status) {
       // alert("mycourses success")
        console.log("Getting courses: " + JSON.stringify(data));
        console.log("Getting courses: " + status);
        $scope.isContentLoaded = true;
        if(data.length === 0 || status === 204){
          //No courses
          $scope.isNoCourses = true;
          
            return;
        }
        $scope.isNoCourses = false;
        $scope.mycourses = data;
        
        $scope.isNoCoach = true;
        for(var i = 0 ; i < data.length; i++){
          if(data[i].isCoach){
            $scope.isNoCoach = false;
            break;
          }
        }
        
        return;
        
        
        
    }).error(function(err,status){
      
//        alert("error getting courses");
    });  
  
  $scope.goto = function(path){
          $location.path(path);
  }
  
  
  
  
    
}]);