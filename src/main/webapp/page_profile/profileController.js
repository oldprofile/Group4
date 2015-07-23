angular.module('myApp.browse')
.controller('ProfileController',['$scope','userService','$routeParams','profileService', function($scope,userService,$routeParams,profileService){
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
    
    
}]);