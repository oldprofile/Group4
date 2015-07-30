angular.module('myApp.browse')
.controller('BrowseMainController',['$scope','getCategories','browseService',"categoriesLocal", function($scope,getCategories, browseService,categoriesLocal){
   
  $scope.featured = [];
  $scope.recommended = [];
  $scope.categories = [];
    
  getCategories.success(function(data){
    $scope.categories = [];
    console.log("categories:" + JSON.stringify(data));
    $scope.categories = angular.copy(data);
    $scope.categories.unshift({id:0,name:"All"});
    categoriesLocal.setCategories(angular.copy($scope.categories));
    
  });
  
  browseService.getFeatured().success(function(data){
    console.log("featured:" + JSON.stringify(data));
    $scope.featured = data;
  });
  
  browseService.getRecommended().success(function(data){
    console.log("recommended:" + JSON.stringify(data));
    $scope.recommended = data;
  });
    

    
}]);