angular.module('myApp.browse')
.controller('ProfileController',['$scope','userService','$routeParams','profileService',
                                 function($scope,userService,$routeParams,profileService){
    var userlogin;                            
    if($routeParams.userLogin === undefined){
        userlogin = userService.getUser().login;
    } else {
        userlogin = $routeParams.userLogin;
    }
        profileService.getUserInfo(userlogin).success(function(data){
        alert(JSON.stringify(data));
        $scope.user = data;
        }).error(function(err,status){
          alert(status);
          alert("Can't get user " + userlogin);
        });                                                     
    
    
}]);