angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', function($scope, mycourses) {

    $scope.dosFilter = {search:"",
                       qType: "All"}
    $scope.isNoCourses = false;
    $scope.mycourses = [];
    $scope.isContentLoaded = false;
    
    mycourses.getAllActualUserCourses().success(function(data) {
      debugger
        alert("mycourses success")
        console.log("Getting courses: " + JSON.stringify(data));
        if(data.length === 0){
            $scope.isNoCourses = true;
            return;
        }
        $scope.isNoCourses = false;
        $scope.mycourses = data;
        
        $scope.isContentLoaded = true;
        
        
    }).error(function(err){
        alert("err getting training");
    });  
    
}]);