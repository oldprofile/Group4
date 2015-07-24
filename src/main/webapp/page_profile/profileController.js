angular.module('myApp.browse')
.controller('ProfileController',['$scope','userService','$routeParams','profileService','$modal', function($scope,userService,$routeParams,profileService,$modal){
    $scope.isContentLoaded = false;
    var userlogin;  
    $scope.studentFeedbacks = [];
    $scope.coachFeedbacks = [];    
    
    $scope.coachArchive = [];
    $scope.studentArchvive = [];   
                                   
    if($routeParams.userLogin === undefined){
        userlogin = userService.getUser().login;
    } else {
        userlogin = $routeParams.userLogin;
    }
                                   
                                   
                                   
                                   
    profileService.getUserInfo(userlogin).success(function(data){
      alert("User" + JSON.stringify(data));
      $scope.user = data;
      $scope.isContentLoaded = true;
      
      profileService.getStudentFeedbacks(data.login).success(function(studentFeedbacks){
        console.log("studentFeedbacks:" + JSON.stringify(studentFeedbacks));
        $scope.studentFeedbacks = studentFeedbacks;
      });
      
      profileService.getCoachFeedbacks(data.login).success(function(coachFeedbacks){
        console.log("coachFeedbacks:" + JSON.stringify(coachFeedbacks));
        $scope.coachFeedbacks = coachFeedbacks;
      });
      
      profileService.getUsersStudentArchive({login:data.login,state:[2,3,5,6]}).success(function(studentArchive){
        console.log("studentArhcive:" + JSON.stringify(studentArchive));
        $scope.studentArchvive = studentArchive;
      });
      
      profileService.getUsersCoachArchive({login:data.login,state:[2,3,5,6]}).success(function(coachArchive){
        console.log("coachArchive:" + JSON.stringify(coachArchive));
        $scope.coachArchive = coachArchive;
      });


    }).error(function(err,status){
      alert(status);
      alert("Can't get user " + userlogin);
    });    
  
  
  
  $scope.leaveStudentFeedback = function(feedback){
    
      var feedbackModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_profile/StudentFeedback.html',
      controller: 'StudentFeedbackController',
      size: "lg",
      resolve: {
        user : function () {
          return $scope.user;
        },
        feedback: function () {
          return feedback;
        }
      }
    });
    
    feedbackModalInstance.result.then(function (feedback) {
//      feedback.feedbackerLogin = userService.getUser().login;  
//      feedbacksService.createTrainingFeedback(feedback);
      alert("Creating new Student Feedback" + JSON.stringify(feedback));
    }, function () {
      //cancel feedback
    });
    
    }
    
    
}])
.controller('StudentFeedbackController',['$scope', '$modalInstance','user','feedback',function($scope,$modalInstance,user,feedback){
  $scope.user = user;
  
  if(feedback === undefined){
    alert("Creating feedback");
    $scope.isView = false;
    
    
    
    $scope.feedback = {
    attendance: true,
    attitude: true,
    commSkills: true,
    motivation: true,
    focusOnResult: true,
      
    assessment: true,
    level: true,
    
      
    other: "",
    
    };
    
  } else{
    alert("Viewing: " + JSON.stringify(feedback));
    $scope.isView = true;
    $scope.feedback = feedback;
  }
  
  
  
  $scope.ok = function () {
    
    $modalInstance.close($scope.feedback);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}]);