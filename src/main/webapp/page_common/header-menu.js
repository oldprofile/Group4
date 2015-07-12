var menuApp = angular.module("myApp.menuApp",[]);
menuApp.directive('headerMenu',function(){
    return {
        restrict: "E",
        templateUrl: "page_common/header-menu.html"
    };
});