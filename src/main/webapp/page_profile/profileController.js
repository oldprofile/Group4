angular.module('myApp.browse')
.controller('ProfileController',['$scope','userService',function($scope,userService){
    
    $scope.user = userService.getUser();
    
}]);