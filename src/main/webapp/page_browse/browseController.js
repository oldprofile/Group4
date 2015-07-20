angular.module('myApp.browse')
.controller('BrowseController',['$scope','$routeParams','browseService', function($scope,$routeParams, browseService){
   
    var categoryID = $routeParams.id;
    
    
    browseService.getTrainingsByCategory(categoryID)
        .success(function(data){
            console.log(JSON.stringify(data));  
            $scope.allTrainings = data;
        
        }).error(function(err){
            
        })

    
}]);