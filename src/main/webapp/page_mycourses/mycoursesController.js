angular.module('myApp.mycourses')
.controller('MyCoursesController', ['$scope','mycourses', function($scope, mycourses) {

    
    mycourses.success(function(data) {
        $scope.mycourses = data;
    });  
    
}]);