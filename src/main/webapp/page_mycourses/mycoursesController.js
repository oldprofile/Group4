angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', function($scope, mycourses) {

    $scope.dosFilter = {search:"",
                       qType: "All"}
    $scope.isNoCourses = false;
    $scope.mycourses = [];
    $scope.isContentLoaded = false;
    
    mycourses.getAllActualUserCourses().success(function(data,status) {
       // alert("mycourses success")
        console.log("Getting courses: " + JSON.stringify(data));
        console.log("Getting courses: " + status);
        if(data.length === 0 || status === 204){
          //No courses
            $scope.isNoCourses = true;
            return;
        }
        $scope.isNoCourses = false;
        $scope.mycourses = data;
        
        $scope.isContentLoaded = true;
        
        
    }).error(function(err,status){
      
        alert("error getting courses");
    });  
    
}]);