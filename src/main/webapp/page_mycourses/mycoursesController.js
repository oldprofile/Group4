angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', function($scope, mycourses) {

    $scope.c = "c"
    mycourses.success(function(data) {
        $scope.mycourses = data;
    });  
    
}]);