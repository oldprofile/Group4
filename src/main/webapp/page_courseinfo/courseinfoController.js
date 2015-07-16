angular.module('myApp.courseinfo')
.controller('CourseInfoController', ['$scope','$routeParams','courseInfoService','userService', function($scope,$routeParams, courseInfoService,userService) {

    $scope.courseName = $routeParams.coursename;
    $scope.subButtonText = "Subscribe";
    
    var courseInfoData = {
        login: userService.getUser().login,
        trainingName: $routeParams.coursename
    };
    
    $scope.isSubscriber = false;
    $scope.subscribe = function(){
        var isSub = $scope.isSubscriber;
        if(isSub){
            alert("leave post");
        } else {
            alert("sub post");
        }
        $scope.subButtonText = !isSub ? "Leave" : "Subscribe";
        $scope.isSubscriber = !isSub;
        
        
    }
    
    courseInfoService.getCourseInfo(courseInfoData).success(function(data){
        //$scope.isSubscriber = false;
        $scope.courseName = $routeParams.coursename;
        
        
    }).error(function(err){
        alert("Can't Access training info");
    });
    
}]);