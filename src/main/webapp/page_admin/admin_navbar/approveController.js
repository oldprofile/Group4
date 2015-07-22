angular.module('myApp.admin')
.controller('ApproveController', ['$scope', 'approveService', function($scope, approveService) {
    $scope.toApproveList=[];
    
    approveService.success(function(data) {
        $scope.toApproveList = data;
        console.log($scope.toApproveList);
    });
    
}]);