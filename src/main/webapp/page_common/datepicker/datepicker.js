var datepickerApp = angular.module('createcourse.datepickerApp',[]);
datepickerApp.directive('datepicker1',function(){
    return {
        restrict: "E",
        require: 'ngModel',
        templateUrl: "page_common/datepicker/datepicker1.html",
        link: function ($scope, elem, attrs) {
        }
    };
});