angular.module('myApp.admin')
.controller('ApproveController', ['$scope', 'approveService', function($scope, approveService) {
    $scope.toApproveList=[];
    
    approveService.getApproveList().then(function(results) {
        $scope.toApproveList = results;
        console.log($scope.toApproveList);
    });
}]);