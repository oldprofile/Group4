'use strict';

angular.module('myApp.browse', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/browse', {
    templateUrl: 'page_browse/browse.html',
    controller: 'BrowseController'
  });
}])

