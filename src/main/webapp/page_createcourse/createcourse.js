'use strict';

angular.module('myApp.createcourse', ['ngRoute', 'ui.bootstrap.datetimepicker'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/createcourse', {
    templateUrl: 'page_createcourse/createcourse.html',
    controller: 'CreateCourseController'
  });
}]);