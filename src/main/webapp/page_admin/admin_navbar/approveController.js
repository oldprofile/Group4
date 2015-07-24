angular.module('myApp.admin')
.controller('ApproveController', ['$scope', 'adminService', function($scope, adminService) {
    $scope.toApproveList=[];
    
    adminService.getApproveList().then(function(results) {
        $scope.toApproveList = results;
        console.log($scope.toApproveList);
    });
}]);