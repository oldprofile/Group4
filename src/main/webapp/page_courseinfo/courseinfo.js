
'use strict';

angular.module('myApp.courseinfo', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/courseinfo/:coursename', {
    templateUrl: 'page_courseinfo/courseinfo.html',
    controller: 'CourseInfoController'
  });
}]);

