'use strict';

angular.module('myApp.mycourses', ['ngRoute','dosHeadToolModule'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/mycourses', {
    templateUrl: 'page_mycourses/mycourses.html',
    controller: 'MyCoursesController'
  });
}]);

