var footerApp = angular.module("myApp.footerApp",[]);
menuApp.directive('myAppFooter',function(){
    return {
        restrict: "E",
        templateUrl: "page_common/footer.html"
    };
});

menuApp.controller("FooterController",['$scope','$location',function($scope, $location){

    $scope.isActive = function (viewLocation) {
     var active = (viewLocation === $location.path());
     return active;
    };
    
}]); 