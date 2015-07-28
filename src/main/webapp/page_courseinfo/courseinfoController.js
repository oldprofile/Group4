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
              $scope.course.subscriber = false;
              $scope.course.promtText = courseInfoService.getPromtText($scope.course);
                $scope.isSubscriber = false;
            }).error(function(err){});
        } else {   
            courseInfoService.subscribe(courseInfoData1).success(function(data){
                $scope.course.subscriber = true;
              $scope.course.promtText = courseInfoService.getPromtText($scope.course);
                $scope.isSubscriber = true;
            }).error(function(err){});
        }    
    }
    
    courseInfoService.getCourseInfo($routeParams.coursename).success(function(data){
        //$scope.isSubscriber = false;
       console.log(JSON.stringify(data));
        $scope.courseName = $routeParams.coursename;
        
        data.courseImg = "assets/angular_bg1.png";
        data.promtText = courseInfoService.getPromtText(data);
        $scope.course = data;
       
        $scope.isSubscriber = data.isSubscriber;
        $scope.isContentLoaded = true;
      
        feedbacksService.getTrainingFeedbacks($scope.courseName)
          .success(function(data){
          $scope.course.feedbacks = data;
        })
        
        
        
    }).error(function(err){
        alert("Can't Access training info");
    });
  
  
  
    
    $scope.leaveFeedback = function(){
      
      var feedbackModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_courseinfo/feedback.html',
      controller: 'LeaveFeedbackModalInstanceController',
      size: "lg",
      resolve: {
        courseinfo : function () {
          return $scope.course;
        }
      }
    });
    
    feedbackModalInstance.result.then(function (feedback) {
      feedback.feedbackerLogin = userService.getUser().login;  
      feedbacksService.createTrainingFeedback(feedback);
    }, function () {
      //cancel feedback
    });
    
    }
    
    $scope.viewFeedback = function(feedback){
      var feedbackModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_courseinfo/feedback.html',
      controller: 'ViewFeedbackModalInstanceController',
      size: "lg",
      resolve: {
        feedback : function(){
          return feedback;
        },
      }
    });
      
    feedbackModalInstance.result.then(function (feedback) {
      //
    }, function () {
      //cancel feedback
    });
    
    }
    
    $scope.editDatePlace = function(index) {
      var dateModalInstance = $modal.open({
        animation: true,
        templateUrl: "page_courseinfo/dateModal.html",
        controller: "EditDateModalInstanceController",
        size: "lg",
        resolve: {
          courseinfo: function() {
            return $scope.course;
          },
          index : function() {
            return index;
          }
        },
      });
      
      dateModalInstance.result.then(function (data) {
      }, function() {
      });
    };
  
  $scope.manageOmissions = function(index) {
    var omissionsModalInstance = $modal.open({
      animation: true,
      templateUrl: "page_courseinfo/omissionsModal.html",
      controller: "OmissionsModalInstanceController",
      size: "lg",
      resolve: {
        courseinfo: function() {
          return $scope.course;
        },
        index : function() {
          return index;
        }
      },
    });
    
    omissionsModalInstance.result.then(function (data) {
    }, function() {
      });
  }
}])



.controller('LeaveFeedbackModalInstanceController',['$scope', '$modalInstance', 'courseinfo', function($scope, $modalInstance, courseinfo){
  $scope.courseinfo = courseinfo;
  $scope.isView = false;
  $scope.feedback = {
    clear: true,
    interesting: true,
    newMaterial: true,
    recommendation: true,
    effective: "5",
    other: "",
    trainingName: courseinfo.name,
  };
  
  $scope.ok = function () {
    
    $modalInstance.close($scope.feedback);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])

.controller('ViewFeedbackModalInstanceController',['$scope', '$modalInstance', 'feedback', function($scope, $modalInstance, feedback){
  
  $scope.feedback = feedback;
  $scope.isView = true;
  
  
  $scope.ok = function () {
    
    $modalInstance.close($scope.feedback);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])

.controller('EditDateModalInstanceController',  ['$scope', '$modalInstance', '$filter', 'courseInfoService', 'courseinfo', 'index', function($scope, $modalInstance, $filter, courseInfoService, courseinfo, index) {
  $scope.courseinfo = courseinfo;
  $scope.index = index;
 
  $scope.ok = function () {
    $scope.courseinfo.dateTime[$scope.index] = $filter('date')($scope.courseinfo.dateTime[$scope.index], 'yyyy-MM-dd HH:mm');
    courseInfoService.editLesson($scope.index + 1, $scope.courseinfo.name, $scope.courseinfo.dateTime[$scope.index],  $scope.courseinfo.places[$scope.index]).then(function(data) {
      return data;
    });
    $modalInstance.close($scope.course, $scope.index);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])

.controller('OmissionsModalInstanceController',  ['$scope', '$modalInstance', 'courseinfo', 'index', function($scope, $modalInstance, courseinfo, index) {
  $scope.courseinfo = courseinfo;
  $scope.index = index;
  $scope.omissionData = [];
  for(var i = 0; i < courseinfo.listeners.length; i++) {
  }
 
  $scope.ok = function () {};
    $modalInstance.close($scope.course, $scope.index);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])
;