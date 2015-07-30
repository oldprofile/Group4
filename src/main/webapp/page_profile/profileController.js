angular.module('myApp.browse')
.controller('ProfileController',['$scope','userService','$routeParams','profileService','$modal','feedbacksService',"$location", function($scope,userService,$routeParams,profileService,$modal,feedbacksService,$location){
    $scope.isContentLoaded = false;
    var userlogin;  
    $scope.studentFeedbacks = [];
    $scope.coachFeedbacks = [];    
    
    $scope.coachArchive = [];
    $scope.studentArchive = [];
  
    $scope.goto = function(path){
      
      $location.path(path);
    }
                                   
    if($routeParams.userLogin === undefined){
        userlogin = userService.getUser().login;
    } else {
        userlogin = $routeParams.userLogin;
    }
                                   
                                   
       var gettingUserProfileData = function(rights){
        //rights access goes here

             var data = $scope.user;

          feedbacksService.getStudentFeedbacks(data.login).success(function(studentFeedbacks){
            console.log("studentFeedbacks:" + JSON.stringify(studentFeedbacks));
            $scope.studentFeedbacks = studentFeedbacks;
          });

          feedbacksService.getCoachFeedbacks(data.login).success(function(coachFeedbacks){
            console.log("coachFeedbacks:" + JSON.stringify(coachFeedbacks));
            $scope.coachFeedbacks = coachFeedbacks;
          });

          profileService.getUsersStudentArchive({login:data.login,state:[2,3,5,6]}).success(function(studentArchive){
            console.log("studentArhcive:" + JSON.stringify(studentArchive));
            $scope.studentArchive = studentArchive;
          });

          profileService.getUsersCoachArchive({login:data.login,state:[2,3,5,6]}).success(function(coachArchive){
            console.log("coachArchive:" + JSON.stringify(coachArchive));
            $scope.coachArchive = coachArchive;
          });
         
       
       }                            
                                   
    profileService.getUserInfo(userlogin).success(function(data){
      //alert("User" + JSON.stringify(data));
      $scope.user = data;
      $scope.isContentLoaded = true;
      gettingUserProfileData();


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
      alert("Creating User Feedback" + JSON.stringify(feedback))   
      feedbacksService.createUserFeedback(feedback).success(function(){
        gettingUserProfileData();
      });
      
    }, function () {
      //cancel feedback
    });
    
    };
  
    $scope.leaveCoachFeedback = function(feedback){
    
      var feedbackModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_profile/CoachFeedback.html',
      controller: 'CoachFeedbackController',
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
      alert("Creating Coach Feedback" + JSON.stringify(feedback))   
      feedbacksService.createCoachFeedback(feedback).success(function(){
        gettingUserProfileData();
      });
      
    }, function () {
      //cancel feedback
    });
    
    };
  
    $scope.settings = function(){
    
      var settingsModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_profile/Settings.html',
      controller: 'SettingsController',
      size: "sm",
      resolve: {
        user : function () {
          return $scope.user;
        }
      }
    });
    
    settingsModalInstance.result.then(function (data) {
         
      //sending new settings
      profileService.saveSettings(data);
      
    }, function () {
      //cancel feedback
    });
    
    };
  
  $scope.requestFeedback = function(){
    
      var settingsModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_profile/Request.html',
      controller: 'RequestController',
      size: "lg",
      resolve: {
        user : function () {
          return $scope.user;
        },
        archive : function () {
          return $scope.studentArchive;
        }
      }
    });
    
    settingsModalInstance.result.then(function (data) {
         
      //sending new settings
      profileService.saveSettings(data);
      
    }, function () {
      //cancel feedback
    });
    
    };
    
    
}])
.controller('StudentFeedbackController',['$scope', '$modalInstance','user','feedback','userService',function($scope,$modalInstance,user,feedback,userService){
  $scope.user = user;
  $scope.isEnglish = false;
  
  if(feedback === undefined){
    alert("Creating feedback");
    $scope.isView = false;
    
    
    
    $scope.feedback = {
      attendance: true,
      attitude: true,
      commSkills: true,
      motivation: true,
      focusOnResult: true,

      assessment: "",
      level: "",


      other: "",
    
    };
    
  } else{
    alert("Viewing: " + JSON.stringify(feedback));
    $scope.isView = true;
    $scope.feedback = feedback;
  }
  
  
  
  $scope.ok = function () {
    $scope.feedback.userLogin = user.login;
    $scope.feedback.feedbackerLogin = userService.getUser().login; 
    $modalInstance.close($scope.feedback);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])
.controller('CoachFeedbackController',['$scope', '$modalInstance','user','feedback','userService',function($scope,$modalInstance,user,feedback,userService){
  $scope.user = user;
  
  
  if(feedback === undefined){
    alert("Creating feedback");
    $scope.isView = false;
    
    
    
    $scope.feedback = {
      howEnounceMaterial: true,
      explainHardness: true,
      highlightMain: true,
      interesting: true,
      askingQuestions: true,
      explainHowToUseNew: true,
      creativity: true,
      kindness: true,
      patience: true,
      erudition: true,
      styleOfTeaching: true,



      other: "",
    
    };
    
  } else {
    alert("Viewing: " + JSON.stringify(feedback));
    $scope.isView = true;
    $scope.feedback = feedback;
  }
  
  
  
  $scope.ok = function () {
    $scope.feedback.coachLogin = user.login;
    $scope.feedback.feedbackerLogin = userService.getUser().login; 
    $modalInstance.close($scope.feedback);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])


.controller('SettingsController',['$scope', '$modalInstance','user','userService',function($scope,$modalInstance,user,userService){
  $scope.user = user;
  $scope.isPhoneOn = true;
  $scope.phoneNumberPattern = /^((8|\+7|\+375)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/;
  if(user.numberPhone === null || user.numberPhone === ""){
    $scope.isPhoneOn = false;
  } else{
    $scope.isPhoneOn = true;
    $scope.phoneNumber = user.numberPhone;
  }
  
  
    
    
    
  
  $scope.ok = function () {
    var data = {};
    
    data.numberPhone = $scope.phoneNumber;
    
    $modalInstance.close(data);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])
.controller('RequestController',['$scope', '$modalInstance','user','archive','userService',function($scope,$modalInstance,user,archive,userService){
  $scope.user = user;
  $scope.archive = angular.copy(archive);
  for(var i = 0; i < $scope.archive.length; i++){
    $scope.archive[i].request = false;
  }
  
    
    
  $scope.check = function(index){
    $scope.archive[index].request = !$scope.archive[index].request;
  }  
  
  $scope.ok = function () {
    var data = {};
    
   alert($scope.archive[0].request);
    
    $modalInstance.close(data);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}]);