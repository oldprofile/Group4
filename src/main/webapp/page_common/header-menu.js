var menuApp = angular.module("myApp.menuApp",[]);
menuApp.directive('headerMenu',function(){
    return {
        restrict: "E",
        templateUrl: "page_common/header-menu.html"
    };
});

menuApp.controller("HeaderMenuController",['$scope','$location',function($scope, $location){

    $scope.isActive = function (viewLocation) {
     var active = (viewLocation === $location.path());
     return active;
    };
    
}]); 