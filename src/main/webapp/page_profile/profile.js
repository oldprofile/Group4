'use strict';

angular.module('myApp.profile', ['ngRoute', "vAccordion"])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile', {
    templateUrl: 'page_profile/profile.html',
    controller: 'ProfileController'
  })
  .when('/profile/:userLogin', {
    templateUrl: 'page_profile/profile.html',
    controller: 'ProfileController'
  });
}])

