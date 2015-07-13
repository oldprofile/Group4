'use strict';

angular.module('myApp.mycourses', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/mycourses', {
    templateUrl: 'page_mycourses/mycourses.html',
    controller: 'MyCoursesController'
  });
}])

.controller('MyCoursesController', [function() {

}]);