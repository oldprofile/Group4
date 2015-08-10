angular.module('myApp.browse')
.controller('BrowseController',['$scope','$routeParams','browseService',"getCategories",'categoriesLocal', function($scope,$routeParams, browseService,getCategories,categoriesLocal){
   $scope.isContentLoaded = false;
    $scope.allTrainings = [{object:"object"}];
    $scope.sort = "dateTraining";
  $scope.changeSort = function(sort){
    $scope.sort = sort;
  }
  $scope.getSortName = function(sort){

    switch(sort){
        case "dateTraining":
        return "Date";
        case "-rating":
        return "Rating";
        case "trainingName":
        return "A to Z";
        
    }
    
    return "";
  
  }
  
  
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