angular.module('myApp.browse')
.controller('ProfileController',['$scope','userService','$routeParams','profileService',
                                 function($scope,userService,$routeParams,profileService){
    if($routeParams.userLogin === undefined){
        $scope.user = userService.getUser();
    } else {
        var userlogin = $routeParams.userLogin;
        profileService.getUserInfo(userlogin).success(function(data){
          alert(JSON.stringify(data));
        $scope.user = data;
        }).error(function(err,status){
          alert(status);
          alert("Can't get user " + userlogin);
        });                                                     
    }
    
}]);