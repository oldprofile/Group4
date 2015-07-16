angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', function($scope, mycourses) {

    
    mycourses.success(function(data) {
        
        console.log("Getting courses: " + JSON.stringify(data));
        
        $scope.mycourses = data;
        
    }).error(function(err){
        alert("err getting training");
    });  
    
    
    
    
}]);