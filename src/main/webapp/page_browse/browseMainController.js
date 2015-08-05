angular.module('myApp.browse')
.controller('BrowseMainController',['$scope','getCategories','browseService',"categoriesLocal","$q", function($scope,getCategories, browseService,categoriesLocal,$q){
   
  $scope.featured = [];
  $scope.recommended = [];
  $scope.categories = [];
  
  $scope.isContentLoaded = false;
  
  
  var categories = getCategories.success(function(data){
    $scope.categories = [];
    console.log("categories:" + JSON.stringify(data));
    $scope.categories = angular.copy(data);
    $scope.categories.unshift({id:0,name:"All"});
    categoriesLocal.setCategories(angular.copy($scope.categories));
    
  });
  
  var featured = browseService.getFeatured().success(function(data){
    console.log("featured:" + JSON.stringify(data));
    $scope.featured = data;
  });
  
  var rec = browseService.getRecommended().success(function(data){
    console.log("recommended:" + JSON.stringify(data));
    $scope.recommended = data;
  });
  $q.all([categories,featured,rec]).then(function(res){
    $scope.isContentLoaded = true;
  })
    

    
}]);