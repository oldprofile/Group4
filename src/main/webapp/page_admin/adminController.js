angular.module('myApp.admin')
.controller('AdminController', ['$scope', function($scope) {
    $scope.pages = [
        {
            name: 'approve',
            url: 'page_admin/admin_navbar/approve.html'
        },
        {
            name: 'statistics',
            url: 'page_admin/admin_navbar/statistics.html'
        },
        {
            name: 'news',
            url: 'page_admin/admin_navbar/news.html'
        }
    ];
    
    $scope.page = $scope.pages[0];
    
    $scope.setPage = function(index) {
        $scope.page = $scope.pages[index];
    };
}]);