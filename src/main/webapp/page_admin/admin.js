'use strict';

angular.module('myApp.admin', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/admin', {
    templateUrl: 'page_admin/admin.html',
    controller: 'AdminController'
  }).when('/editcourse/:coursename', {
    templateUrl: 'page_createcourse/createcourse.html',
    controller: 'EditCourseController'
  });
}]);


