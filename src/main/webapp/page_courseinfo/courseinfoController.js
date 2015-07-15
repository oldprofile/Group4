angular.module('myApp.courseinfo')
.controller('CourseInfoController', ['$scope','$routeParams','courseInfoService','userService', function($scope,$routeParams, courseInfoService,userService) {

    $scope.courseName = $routeParams.coursename;
    
    var courseInfoData = {
        login: userService.getUser().login,
        trainingName: $routeParams.coursename
    };
    alert(courseInfoData.login);
    courseInfoService.getCourseInfo(courseInfoData).success(function(data){
        alert("Success");
    }).error(function(err){
        alert("Error");
    });
    
}]);