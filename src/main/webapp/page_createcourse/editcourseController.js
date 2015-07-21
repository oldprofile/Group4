angular.module('myApp.createcourse')
.controller('EditCourseController', ['$scope', '$routeParams', 'createcourse', 'courseInfoService', 'initCourseService', function($scope, $routeParams, createcourse, courseInfoService, initCourseService) {
    $scope.isEdited = true;
    
    initCourseService($scope);
    
    courseInfoService.getCourseInfo($routeParams.coursename).success(function(data) {
        $scope.courseInfo = angular.copy(data);
        console.log(data.dateTime.length);
        console.log($scope.courseInfo);
    });
}]);