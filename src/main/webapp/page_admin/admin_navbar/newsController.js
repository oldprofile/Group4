angular.module('myApp.admin')
.controller('NewsController', ['$scope', 'adminService', function($scope, adminService) {
    $scope.newsList=[];
    $scope.pageNumber = 1;
    
    $scope.getNews = function(pageNumber) {
        adminService.getNews(pageNumber).then(function(data) {
            $scope.newsList = data;
            console.log($scope.newsList);
        });
    };
    
    $scope.getNews($scope.pageNumber);
    
}]);