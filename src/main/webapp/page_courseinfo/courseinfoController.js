angular.module('myApp.courseinfo')
.controller('CourseInfoController', ['$scope','$routeParams','courseInfoService','userService','$modal','feedbacksService', function($scope,$routeParams, courseInfoService,userService,$modal,feedbacksService) {

    $scope.courseName = $routeParams.coursename;
    $scope.subButtonText = "Subscribe";
    $scope.isContentLoaded = false;
    $scope.course = {};
    
    
    
  
    
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
    
    courseInfoService.getCourseInfo($routeParams.coursename).success(function(data){
        //$scope.isSubscriber = false;
        $scope.courseName = $routeParams.coursename;
        console.log(JSON.stringify(data));
        $scope.course = data;
        $scope.isSubscriber = data.subscriber;
        $scope.isContentLoaded = true;
        
        
        
    }).error(function(err){
        alert("Can't Access training info");
    });
    
    $scope.leaveFeedback = function(){

      var feedbackModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_courseinfo/feedback.html',
      controller: 'FeedbackModalInstanceController',
      size: "lg",
      resolve: {
        courseinfo : function () {
          return $scope.course;
        }
      }
    });
    
    feedbackModalInstance.result.then(function (feedback) {
      feedbacksService.createTrainingFeedback(feedback);
    }, function () {
      //cancel feedback
    });
    
    }
    
}])
.controller('FeedbackModalInstanceController',['$scope', '$modalInstance', 'courseinfo', function($scope, $modalInstance, courseinfo){
  $scope.courseinfo = courseinfo;
  
  $scope.feedback = {
    clear: true,
    interesting: true,
    newMaterial: true,
    recommendation: true,
    effective: "5",
    other: "",
  }
  
  $scope.ok = function () {
    alert(JSON.stringify($scope.feedback))
    $modalInstance.close($scope.feedback);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}]);