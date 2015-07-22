var datepickerApp = angular.module('createcourse.datepickerApp',[]);
datepickerApp.directive('datepicker1',function(){
    return {
        restrict: "E",
        require: "ngModel",
        templateUrl: "page_common/datepicker/datepicker1.html",
        link: function ($scope, elem, attrs) {
            
        }
    };
});

datepickerApp.directive('scroll', function($timeout) {
  return {
    restrict: 'A',
    link: function(scope, element, attr) {
      scope.$watchCollection(attr.scroll, function(newVal) {
        $timeout(function() {
         element[0].scrollTop = element[0].scrollHeight;
        });
      });
    }
  }
});
