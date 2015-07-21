angular.module('myApp.admin')
.controller('AdminController', ['$scope', 'getToApproveList', function($scope, getToApproveList) {
    $scope.toApproveList=[{name: 'Java', status: 'needs approval'}, {name: 'Angular', status: 'was edited'}, {name: 'Java', status: 'needs approval'}, {name: 'Angular', status: 'was edited'}, {name: 'Java', status: 'needs approval'}, {name: 'Angular', status: 'was edited'}, {name: 'Java', status: 'needs approval'}, {name: 'Angular', status: 'was edited'}, {name: 'Java', status: 'needs approval'}, {name: 'Angular', status: 'was edited'}];
    
    
    /*getToApproveList.success(function(data) {
        $scope.toApproveList = data;
    });*/
}]);