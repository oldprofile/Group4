angular.module('createcourse.datepickerApp')
    .controller("DatepickerController",['$scope', function($scope, $location, $anchorScroll){
        $scope.test = 1;   
        $scope.pages = [
            {
                name: 'onceonly',
                url: 'page_common/datepicker/onceonly.html'
            },
            {
                name: 'repeating',
                url: 'page_common/datepicker/repeating.html'
            }
        ];
        
        $scope.page = $scope.pages[0];
        
        $scope.setPage = function(index) {
            $scope.page = $scope.pages[index];
        };
        
        $scope.addDate = function() {
            $scope.courseInfo.dateTime.push("");        
        };
        
        $scope.deleteDate = function(index) {
            $scope.courseInfo.dateTime.splice(index, 1);
        };
}]); 