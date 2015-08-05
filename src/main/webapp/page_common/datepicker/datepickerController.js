angular.module('createcourse.datepickerApp')
    .controller("DatepickerController",['$scope', function($scope, $location, $anchorScroll){  
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
        
        $scope.setPage = function(index, isRepeating) {
            $scope.page = $scope.pages[index];
            $scope.courseInfo.isRepeating = isRepeating;
        };
        
        $scope.addDate = function() {
            $scope.temp.tempDates.push("");        
        };
        
        $scope.deleteDate = function(index) {
            $scope.temp.tempDates.splice(index, 1);
        };
}]); 