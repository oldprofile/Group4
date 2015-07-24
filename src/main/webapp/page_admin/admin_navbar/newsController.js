angular.module('myApp.admin')
.controller('NewsController', ['$scope', 'newsService', function($scope, newsService) {
    $scope.newsList=[];
    
    newsService.getNews().success(function(data) {
        $scope.newsList = data;
        console.log($scope.newsList);
    });
    
}]);