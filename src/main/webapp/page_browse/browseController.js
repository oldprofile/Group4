angular.module('myApp.browse')
.controller('BrowseController',['$scope','browseService', function($scope, browseService){
    alert("browse");
    browseService.getAllTrainings()
        .success(function(data){
              
            $scope.allTrainings = data;
        
        }).error(function(err){
            alert("err");
        })

    
}]);