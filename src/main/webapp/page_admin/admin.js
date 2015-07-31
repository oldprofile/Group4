'use strict';

angular.module('myApp.admin', ['ngRoute', 'ui.bootstrap.pagination'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/admin', {
    templateUrl: 'page_admin/admin.html',
    controller: 'AdminController'
  }).when('/editcourse/:coursename/:coursestate', {
    templateUrl: 'page_createcourse/createcourse.html',
    controller: 'EditCourseController'
  });
}]);


