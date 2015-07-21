
'use strict';

angular.module('myApp.courseinfo', ['ngRoute','ui.bootstrap',
    'ui.bootstrap.tpls','feedbacks.myApp'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/courseinfo/:coursename', {
    templateUrl: 'page_courseinfo/courseinfo.html',
    controller: 'CourseInfoController'
  }).otherwise('/mycourses');
}]);

