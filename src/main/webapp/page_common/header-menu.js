var menuApp = angular.module("myApp.menuApp",[]);
menuApp.directive('headerMenu',function(){
    return {
        restrict: "E",
        templateUrl: "page_common/header-menu.html"
    };
});

menuApp.controller("HeaderMenuController",['$scope','$location',"getCategories",function($scope, $location,getCategories){
    $scope.categories = [];
    $scope.isActive = function (viewLocation) {
     var viewLocationArray = viewLocation.split("/");
     var locationArray = $location.path().split("/");
     
     var active = (locationArray[1] === viewLocationArray[1]);
     return active;
    };
    
    getCategories.success(function(data){
                          alert(JSON.stringify(data))
                          $scope.categories = data;
    }).error(function(err){
        alert("Can't get categories")
    })
}])
                          
 