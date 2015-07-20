angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', function($scope, mycourses) {

    
    $scope.isNoCourses = false;
    $scope.mycourses = [];
   
    
    mycourses.success(function(data) {
        
        console.log("Getting courses: " + JSON.stringify(data));
        if(data.length == 0){
            $scope.isNoCourses = true;
            return;
        }
        $scope.isNoCourses = false;
        $scope.mycourses = data;
        
        
        
    }).error(function(err){
        alert("err getting training");
    });  
    
    
    
    
}]);