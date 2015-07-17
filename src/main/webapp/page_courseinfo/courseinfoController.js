angular.module('myApp.courseinfo')
.controller('CourseInfoController', ['$scope','$routeParams','courseInfoService','userService', function($scope,$routeParams, courseInfoService,userService) {

    $scope.courseName = $routeParams.coursename;
    $scope.subButtonText = "Subscribe";
    
    var courseInfoData = {
        login: userService.getUser().login,
        trainingName: $routeParams.coursename
    };
    
    var courseInfoData1 = {
        login: userService.getUser().login,
        nameTraining: $routeParams.coursename
    };
    
    $scope.isSubscriber = false;
    $scope.subscribe = function(){
        var isSub = $scope.isSubscriber;
        if(isSub){
            courseInfoService.leave(courseInfoData1).success(function(data){
                
                $scope.isSubscriber = false;
            }).error(function(err){});
        } else {   
            courseInfoService.subscribe(courseInfoData1).success(function(data){
                
                $scope.isSubscriber = true;
            }).error(function(err){});
        }    
    }
    
    courseInfoService.getCourseInfo(courseInfoData).success(function(data){
        //$scope.isSubscriber = false;
        $scope.courseName = $routeParams.coursename;
        console.log(JSON.stringify(data));
        $scope.course = data;
        $scope.isSubscriber = data.subscriber;
        
        
        
        
    }).error(function(err){
        alert("Can't Access training info");
    });
    
}]);