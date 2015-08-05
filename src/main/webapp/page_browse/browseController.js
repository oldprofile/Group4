angular.module('myApp.browse')
.controller('BrowseController',['$scope','$routeParams','browseService',"getCategories",'categoriesLocal', function($scope,$routeParams, browseService,getCategories,categoriesLocal){
   $scope.isContentLoaded = false;
    var categoryID = $routeParams.id;
    $scope.categoryName = "" 
    categoriesLocal.getCategoryNameById(categoryID).then(function(categoryName){
      $scope.categoryName = categoryName;
    });
    
    
    browseService.getTrainingsByCategory(categoryID)
        .success(function(data){
            console.log(JSON.stringify(data));  
            $scope.allTrainings = data;
            
            $scope.isContentLoaded = true;
        }).error(function(err){
            
        })

    
}]);