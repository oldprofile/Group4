
'use strict';

angular.module('myApp.createcourse', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/createcourse', {
    templateUrl: 'page_createcourse/createcourse.html',
    controller: 'CreateCourseController'
  });
}])

.controller('CreateCourseController', ['$scope', function($scope) {
    $scope.categories = [];
    $scope.accessibility = ['Internal', 'External'];
    $scope.repeat = ['Once-only', 'Repeating'];
    $scope.languages = ['Russian', 'English'];
    $scope.courseInfo = {};
    $scope.courseInfo.categories = $scope.categories[0];
    $scope.courseInfo.accessibility = $scope.accessibility[0];
    $scope.courseInfo.language = $scope.languages[0];
    $scope.courseInfo.repeat = $scope.repeat[0];
    $scope.saveData = function() {
        console.log($scope.courseInfo);
    };
}]);