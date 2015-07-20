'use strict';

angular.module('myApp.createcourse', ['ngRoute', 'ui.bootstrap.datetimepicker', 'ui.bootstrap'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/createcourse', {
    templateUrl: 'page_createcourse/createcourse.html',
    controller: 'CreateCourseController'
  }).when('/editcourse/:coursename', {
    templateUrl: 'page_createcourse/createcourse.html',
    controller: 'EditCourseController'
  });
}]);