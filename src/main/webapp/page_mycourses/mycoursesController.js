angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', function($scope, mycourses) {

    
    mycourses.success(function(data) {
        alert(JSON.stringify(data))
        $scope.mycourses = data;
    }).error(function(err){
        alert("err");
    });  
    
}]);