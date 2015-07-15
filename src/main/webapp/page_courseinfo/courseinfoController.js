angular.module('myApp.courseinfo')
.controller('CourseInfoController', ['$scope','$routeParams','courseInfoService', function($scope,$routeParams, courseInfoService) {

    $scope.courseName = $routeParams.coursename;
    
    var courseInfoData = {
        login: "Dosant",
        trainingName: $routeParams.coursename
    };
    
    courseInfoService.getCourseInfo(courseInfoData).success(function(data){
        alert("Success");
    }).error(function(err){
        alert("Error");
    });
    
}]);