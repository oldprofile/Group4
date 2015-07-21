angular.module('myApp.createcourse')
.controller('EditCourseController', ['$scope', '$routeParams', 'createcourse', 'courseInfoService', 'initCourseService', function($scope, $routeParams, createcourse, courseInfoService, initCourseService) {
    $scope.isEdited = 'true';
    
    initCourseService($scope);
    
    courseInfoService.getCourseInfo($routeParams.coursename).success(function(data) {
        $scope.courseInfo = angular.copy(data);
        console.log(data.dateTime.length);
        $scope.courseInfo.dateTime = [];
        for(var i = 0; i < data.dateTime.length; i++) {
            $scope.courseInfo.dateTime.push({id: i, name: data.dateTime[i]});
        };
        console.log($scope.courseInfo);
    });
}]);