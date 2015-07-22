angular.module('myApp.browse')
.controller('ProfileController',['$scope','userService', 'getFeedbacksOnUserService', function($scope, userService, getFeedbacksOnUserService){
    
    $scope.user = userService.getUser();
    
    $scope.feedbackList = [];
    
    getFeedbacksOnUserService.success(function(data) {
        $scope.feedbackList = data;
        console.log($scope.feedbackList);
    });
    
}]);