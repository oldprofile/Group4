angular.module('myApp.admin')
.controller('ApproveController', ['$scope', '$location', 'adminService', function($scope, $location, adminService) {
    $scope.toApproveList=[];

    $scope.goTo = function(path) {
        console.log(path);
      $location.path(path);
    };
    
    adminService.getApproveList().then(function(results) {
        $scope.toApproveList = results;
        console.log($scope.toApproveList);
    });
}]);