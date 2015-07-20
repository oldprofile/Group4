angular.module('myApp.createcourse')
.controller('EditCourseController', ['$scope', '$routeParams', 'createcourse', 'courseInfoService', 'initCourseService', function($scope, $routeParams, createcourse, courseInfoService, initCourseService) {
    initCourseService($scope);
    courseInfoService.getCourseInfo($routeParams.coursename).success(function(data) {
        $scope.courseInfo = data;
    });
}]);