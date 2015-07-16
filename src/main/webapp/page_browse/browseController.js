angular.module('myApp.browse')
.controller('BrowseController',['$scope','browseService', function($scope, browseService){
    
    browseService.getAllTrainings
        .success(function(data){
              
        $scope.allTraining = data;
        
        })
    
}]);